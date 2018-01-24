# -*- coding: utf-8 -*-
############################################################################
# Copyright 2017 OceanScan - Marine Systems & Technology, Lda.             #
############################################################################
# Licensed under the Apache License, Version 2.0 (the "License");          #
# you may not use this file except in compliance with the License.         #
# You may obtain a copy of the License at                                  #
#                                                                          #
# http://www.apache.org/licenses/LICENSE-2.0                               #
#                                                                          #
# Unless required by applicable law or agreed to in writing, software      #
# distributed under the License is distributed on an "AS IS" BASIS,        #
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. #
# See the License for the specific language governing permissions and      #
# limitations under the License.                                           #
############################################################################
# Author: Ricardo Martins                                                  #
############################################################################

import io
import os
import re
import shutil
import xml.etree.ElementTree as Et

from . import base
from .. import beautify

# Default package.
PACKAGE = 'pt.lsts.imc'


def under_to_camel(s):
    """Convert lower case words separated by underscores to camel case."""
    def camelcase():
        yield str.lower
        while True:
            yield str.capitalize

    c = camelcase()
    return "".join(next(c)(x) if x else '_' for x in s.split("_"))


def camel_to_under(s):
    """Convert a camel case string to a lower case underscore separated string."""
    s = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', s)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s).lower()


def get_java_type(field):
    xml_type = field.get('type')
    if xml_type == 'int8_t':
        return 'byte'
    elif xml_type in ['uint8_t', 'int16_t']:
        return 'short'
    elif xml_type in ['uint32_t', 'int64_t']:
        return 'long'
    elif xml_type in ['fp32_t', 'fp64_t']:
        return 'double'
    elif xml_type == 'plaintext':
        return 'String'
    elif xml_type == 'rawdata':
        return 'byte[]'
    elif xml_type == 'message':
        return get_submessage_type(field)
    elif xml_type == 'message-list':
        return 'java.util.Vector<%s>' % (get_submessage_type(field))
    return 'int'


def get_java_type_setter(field):
    if field.get('type') == 'message-list':
        return 'java.util.Collection<%s>' % (get_submessage_type(field))
    return get_java_type(field)


def get_java_getter(field):
    """Retrieve the Java getter of a given field."""
    xml_type = field.get('type')
    if xml_type == 'int8_t':
        return 'getByte'
    elif xml_type in ['uint8_t', 'int16_t']:
        return 'getShort'
    elif xml_type in ['uint16_t', 'int32_t']:
        return 'getInteger'
    elif xml_type in ['uint32_t', 'int64_t']:
        return 'getLong'
    elif xml_type in ['fp32_t', 'fp64_t']:
        return 'getDouble'
    elif xml_type == 'rawdata':
        return 'getRawData'
    elif xml_type == 'plaintext':
        return 'getTupleList' if field.get('unit') == 'TupleList' else 'getString'
    elif xml_type == 'message':
        return 'getMessageOrNull'
    elif xml_type == 'message-list':
        return 'getMessageListOrNull'
    return 'getInteger'


def gen_bitfield_values(fd, xml_root, xml_msg):
    for xml_field in xml_msg.findall("field[@unit='Bitfield']"):
        bitfield_def = xml_field.get('bitfield-def')
        if bitfield_def is None:
            bitfield_prefix = xml_field.get('prefix')
            xml_node = xml_field.findall('value')
        else:
            xml_node = xml_root.find("bitfields/def[@abbrev='%s']" % bitfield_def)
            bitfield_prefix = xml_node.get('prefix')
            xml_node = xml_node.findall("value")

        for xml_value in xml_node:
            fd.write('public static final %s %s_%s = %s;\n' % (get_java_type(xml_field), bitfield_prefix, xml_value.get('abbrev'), xml_value.get('id')))
    fd.write('\n')


def gen_enum(fd, xml_root, xml_field):
    java_name = get_java_enum_name(xml_field)

    fd.write('    public enum %s {\n' % java_name)

    if xml_field.get('enum-def') is None:
        xml_values = xml_field.findall('value')
    else:
        xml_values = xml_root.findall("enumerations/def[@abbrev='%s']/value" % xml_field.get('enum-def'))

    field_index = 0
    for xml_value in xml_values:
        fd.write('        %s(%s)' % (xml_value.get('abbrev'), xml_value.get('id')))
        field_index += 1
        if field_index == len(xml_values):
            fd.write(';\n')
        else:
            fd.write(',\n')

    fd.write('\n')
    fd.write('        protected long value;\n\n')

    fd.write('        public long value() {\n')
    fd.write('            return value;\n')
    fd.write('        }\n\n')

    fd.write('        %s(long value) {\n' % java_name)
    fd.write('            this.value = value;\n')
    fd.write('        }\n')
    fd.write('    }\n\n')


def gen_message_group(xml_root, xml_group, dest_folder):
    class_name = xml_group.get('abbrev')
    fname = os.path.join(dest_folder, class_name + '.java')
    print("* %s" % fname)
    fd = open(fname, 'w')
    fd.write('package %s;\n' % PACKAGE)
    fd.write('\n')
    fd.write('public class %s extends IMCMessage {\n' % class_name)

    # Constructors.
    fd.write('    public %s(int type) {\n' % class_name)
    fd.write('        super(type);\n')
    fd.write('    }\n\n')

    fd.write('    public %s(IMCDefinition defs, int type) {\n' % class_name)
    fd.write('        super(defs, type);\n')
    fd.write('    }\n\n')

    # Clone.
    fd.write('    public static %s clone(IMCMessage msg) throws Exception {\n' % class_name)
    fd.write('        IMCMessage m = IMCDefinition.getInstance().create(msg.getAbbrev());\n')
    fd.write('        if (!Maneuver.class.isAssignableFrom(m.getClass()))\n')
    fd.write(
        '            throw new Exception(m.getClass().getSimpleName() + " is not a subclass of %s");\n' % class_name)
    fd.write('\n')
    fd.write('        if (msg.definitions != m.definitions) {\n')
    fd.write('            msg = msg.cloneMessage();\n')
    fd.write('            IMCUtil.updateMessage(msg, m.definitions);\n')
    fd.write('        }\n')
    fd.write('\n')
    fd.write('        m.getHeader().values.putAll(msg.getHeader().values);\n')
    fd.write('        m.values.putAll(msg.values);\n')
    fd.write('\n')
    fd.write('        return (%s)m;\n' % class_name)
    fd.write('    }\n')
    fd.write('}\n')
    fd.close()


def gen_message(xml_root, xml_msg, dest_folder):
    class_name = xml_msg.get('abbrev')
    fname = os.path.join(dest_folder, class_name + '.java')
    print("* %s" % fname)
    fd = open(fname, 'w')
    fd.write('package pt.lsts.imc;\n')
    fd.write('\n')
    fd.write('public class %s extends %s {\n' % (class_name, get_message_superclass(xml_root, xml_msg)))
    fd.write('    public static final int ID_STATIC = %s;\n\n' % xml_msg.get('id'))

    gen_bitfield_values(fd, xml_root, xml_msg)

    # Inline enumerations.
    for enum in xml_msg.findall("field[@unit='Enumerated']"):
        gen_enum(fd, xml_root, enum)

    # Constructors.
    fd.write('    public %s() {\n' % class_name)
    fd.write('        super(ID_STATIC);\n')
    fd.write('    }\n\n')

    fd.write('    public %s(IMCDefinition defs) {\n' % class_name)
    fd.write('        super(defs, ID_STATIC);\n')
    fd.write('    }\n\n')

    fd.write('    public %s(IMCMessage msg) {\n' % class_name)
    fd.write('        super(ID_STATIC);\n')
    fd.write('        try {\n')
    fd.write('            copyFrom(msg);\n')
    fd.write('        } catch (Exception e) {\n')
    fd.write('            e.printStackTrace();\n')
    fd.write('        }\n')
    fd.write('    }\n\n')

    # Create.
    fd.write('    public static %s create(Object... values) {\n' % class_name)
    fd.write('        %s m = new %s();\n' % (class_name, class_name))
    fd.write('        for (int i = 0; i < values.length - 1; i += 2)\n')
    fd.write('            m.setValue(values[i].toString(), values[i + 1]);\n')
    fd.write('        return m;\n')
    fd.write('    }\n\n')

    # Clone.
    fd.write('    public static %s clone(IMCMessage msg) throws Exception {\n' % class_name)
    fd.write('        %s m = new %s();\n' % (class_name, class_name))
    fd.write('        if (msg == null)\n')
    fd.write('            return m;\n')
    fd.write('        if (msg.definitions != m.definitions) {\n')
    fd.write('            msg = msg.cloneMessage();\n')
    fd.write('            IMCUtil.updateMessage(msg, m.definitions);\n')
    fd.write('        } else if (msg.getMgid() != m.getMgid()) {\n')
    fd.write('            throw new Exception("incompatible types: " + msg.getAbbrev() + " and " + m.getAbbrev());\n')
    fd.write('        }\n')
    fd.write('        m.getHeader().values.putAll(msg.getHeader().values);\n')
    fd.write('        m.values.putAll(msg.values);\n')
    fd.write('        return m;\n')
    fd.write('    }\n\n')

    # Getter/Setters.
    for xml_field in xml_msg.findall('field'):
        gen_getter_setter(fd, class_name, xml_field)

    fd.write('}\n')
    fd.close()


def get_submessage_type(xml_field):
    return 'IMCMessage' if xml_field.get('message-type') is None else xml_field.get('message-type')


def get_message_superclass(xml_root, xml_msg):
    msg_abbrev = xml_msg.get('abbrev')
    for xml_msg_group in xml_root.findall('message-groups/message-group'):
        if xml_msg_group.find("message-type[@abbrev='%s']" % msg_abbrev) is not None:
            return xml_msg_group.get('abbrev')
    return 'IMCMessage'


def get_java_enum_name(xml_field):
    if xml_field.get('abbrev'):
        name = xml_field.get('abbrev')
    else:
        name = xml_field.get('enum-def')
    return camel_to_under(name).upper()


def gen_getter(fd, xml_field, camel_case):
    java_type = get_java_type(xml_field)
    java_getter = get_java_getter(xml_field)
    field_name = xml_field.get('abbrev')

    method_name = 'get_' + field_name
    if camel_case:
        method_name = under_to_camel(method_name)

    if xml_field.get('unit') == 'Enumerated':
        enum_name = get_java_enum_name(xml_field)
        fd.write('    public %s %s() {\n' % (enum_name, method_name))
        fd.write('        try {\n')
        fd.write(
            '            %s o = %s.valueOf(getMessageType().getFieldPossibleValues("%s").get(getLong("%s")));\n' % (
                enum_name, enum_name, field_name, field_name))
        fd.write('            return o;\n')
        fd.write('        } catch (Exception e) {\n')
        fd.write('            return null;\n')
        fd.write('        }\n')
        fd.write('    }\n\n')

        fd.write('    public String %sStr() {\n' % method_name)
        fd.write('        return getString("%s");\n' % field_name)
        fd.write('    }\n\n')

        fd.write('    public %s %sVal() {\n' % (java_type, method_name))
        fd.write('        return %s("%s");\n' % (java_getter, field_name))
        fd.write('    }\n\n')

    else:
        if java_getter == 'getTupleList':
            java_type = 'java.util.LinkedHashMap<String, String>'

        fd.write('    public %s %s() {\n' % (java_type, method_name))
        if xml_field.get('type') == 'message-list':
            fd.write('        return %s("%s", %s.class);\n' % (java_getter, field_name, get_submessage_type(xml_field)))
        elif xml_field.get('type') == 'message':
            fd.write('        return %s(%s.class, "%s");\n' % (java_getter, get_submessage_type(xml_field), get_submessage_type(xml_field)))
        else:
            fd.write('        return %s("%s");\n' % (java_getter, field_name))
        fd.write('    }\n\n')

    if xml_field.get('type') == 'message':
        fd.write('    public <T extends IMCMessage> T %s(Class<T> clazz) throws Exception {\n' % method_name)
        fd.write('        return getMessage(clazz, "%s");\n' % field_name)
        fd.write('    }\n\n')


def gen_setter(fd, class_name, xml_field, camel_case):
    java_type = get_java_type_setter(xml_field)
    field_name = xml_field.get('abbrev')
    method_name = 'set_' + field_name
    if camel_case:
        method_name = under_to_camel(method_name)
    var_name = field_name

    if xml_field.get('unit') == 'Enumerated':
        enum_name = get_java_enum_name(xml_field)
        fd.write('    public %s %sStr(String %s) {\n' % (class_name, method_name, field_name))
        fd.write('        setValue("%s", %s);\n' % (field_name, field_name))
        fd.write('        return this;\n')
        fd.write('    }\n\n')

        fd.write('    public %s %sVal(%s %s) {\n' % (class_name, method_name, enum_name, field_name))
        fd.write('        setValue("%s", %s);\n' % (field_name, field_name))
        fd.write('        return this;\n')
        fd.write('    }\n\n')
        java_type = enum_name
        var_name += ".value()"

    if xml_field.get('unit') == 'TupleList':
        fd.write('    public %s %s(java.util.LinkedHashMap<String, ?> %s) {\n' % (class_name, method_name, field_name))
        fd.write('        values.put("%s", encodeTupleList(%s));\n' % (field_name, field_name))
        fd.write('        return this;\n')
        fd.write('    }\n')
        fd.write('\n')

    fd.write('    public %s %s(%s %s) {\n' % (class_name, method_name, java_type, field_name))
    fd.write('        values.put("%s", %s);\n' % (field_name, var_name))
    fd.write('        return this;\n')
    fd.write('    }\n')
    fd.write('\n')


def gen_getter_setter(fd, class_name, xml_field, camel_case=True):
    gen_getter(fd, xml_field, camel_case)
    gen_setter(fd, class_name, xml_field, camel_case)


def gen_header_file(root, xml_md5, dest_folder, dest_file):
    fd = open(os.path.join(dest_folder, dest_file), 'w')

    fd.write('package %s;' % PACKAGE)
    fd.write('\n\n')
    fd.write('public class Header extends IMCMessage {\n')
    fd.write('    public Header() {\n')
    fd.write('        super(IMCDefinition.getInstance().getHeaderType());\n')
    fd.write('    }\n')
    fd.write('\n')
    fd.write('    public Header(IMCDefinition defs) {\n')
    fd.write('        super(defs.getHeaderType());\n')
    fd.write('    }\n')
    fd.write('\n')

    for field in root.findall('header/field'):
        gen_getter_setter(fd, 'Header', field, camel_case=False)

    fd.write('}\n')


def gen_message_factory(xml_root, dest_folder):
    c = io.StringIO()

    c.write('package %s;' % PACKAGE)
    c.write('\n\n')
    c.write('public class MessageFactory {\n')

    c.write('private static MessageFactory instance = null;\n')
    c.write('\n')

    # Constructor.
    c.write('private MessageFactory() {\n}\n')
    c.write('\n')

    # getInstance().
    c.write('public static MessageFactory getInstance() {\n')
    c.write('    if (instance == null) {\n')
    c.write('        instance = new MessageFactory();\n')
    c.write('    }\n')
    c.write('    return instance;\n')
    c.write('}\n\n')

    # createTypedMessage(String).
    c.write('public IMCMessage createTypedMessage(String msgName, IMCDefinition defs) {\n')
    c.write('    return createTypedMessage(defs.getMessageId(msgName), defs);\n')
    c.write('}\n\n')

    # createTypedMessage(int).
    c.write('private IMCMessage createTypedMessage(int mgid, IMCDefinition defs) {\n')
    c.write('    switch (mgid) {\n')
    for xml_message in xml_root.findall('message'):
        c.write('        case %s.ID_STATIC:\n' % xml_message.get('abbrev'))
        c.write('        return new %s(defs);\n' % xml_message.get('abbrev'))
    c.write('    default:\n')
    c.write('        return new IMCMessage(defs);\n')
    c.write('    }\n')
    c.write('}\n')

    c.write('}\n')
    with open(os.path.join(dest_folder, 'MessageFactory.java'), 'w') as fd:
        fd.write(beautify.beautify(c.getvalue(), 4))
    c.close()


def main(xml, out_folder, no_base, force):
    base_folder = os.path.join(out_folder)
    package_folder = PACKAGE.split('.')

    dest_folder = os.path.join(base_folder, "src", "generated", "java", *package_folder)
    res_folder = os.path.join(base_folder, "src", "generated", "resources")

    # Parse XML specification.
    tree = Et.parse(xml)
    root = tree.getroot()

    # Install support files.
    if not no_base:
        base.install(base_folder)

    # Install resources.
    os.makedirs(res_folder, exist_ok=True)
    shutil.copy(xml, res_folder)

    # Create destination folder.
    os.makedirs(dest_folder, exist_ok=True)

    gen_header_file(root, None, dest_folder, 'Header.java')
    gen_message_factory(root, dest_folder)

    # Message groups.
    for msg in root.findall('message-groups/message-group'):
        gen_message_group(root, msg, dest_folder)

    for msg in root.findall('message'):
        gen_message(root, msg, dest_folder)
