/*
 * Below is the copyright agreement for IMCJava.
 *
 * Copyright (c) 2010-2016, Laboratório de Sistemas e Tecnologia Subaquática
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     - Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     - Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     - Neither the names of IMC, LSTS, IMCJava nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL LABORATORIO DE SISTEMAS E TECNOLOGIA SUBAQUATICA
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package pt.lsts.imc;

import pt.lsts.imc.types.PlanSpecificationAdapter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

/**
 * @author zp
 */
public class IMCUtil {
    public final static int[] crc16_table = new int[]{
            0x0000, 0xC0C1, 0xC181, 0x0140, 0xC301, 0x03C0, 0x0280, 0xC241,
            0xC601, 0x06C0, 0x0780, 0xC741, 0x0500, 0xC5C1, 0xC481, 0x0440,
            0xCC01, 0x0CC0, 0x0D80, 0xCD41, 0x0F00, 0xCFC1, 0xCE81, 0x0E40,
            0x0A00, 0xCAC1, 0xCB81, 0x0B40, 0xC901, 0x09C0, 0x0880, 0xC841,
            0xD801, 0x18C0, 0x1980, 0xD941, 0x1B00, 0xDBC1, 0xDA81, 0x1A40,
            0x1E00, 0xDEC1, 0xDF81, 0x1F40, 0xDD01, 0x1DC0, 0x1C80, 0xDC41,
            0x1400, 0xD4C1, 0xD581, 0x1540, 0xD701, 0x17C0, 0x1680, 0xD641,
            0xD201, 0x12C0, 0x1380, 0xD341, 0x1100, 0xD1C1, 0xD081, 0x1040,
            0xF001, 0x30C0, 0x3180, 0xF141, 0x3300, 0xF3C1, 0xF281, 0x3240,
            0x3600, 0xF6C1, 0xF781, 0x3740, 0xF501, 0x35C0, 0x3480, 0xF441,
            0x3C00, 0xFCC1, 0xFD81, 0x3D40, 0xFF01, 0x3FC0, 0x3E80, 0xFE41,
            0xFA01, 0x3AC0, 0x3B80, 0xFB41, 0x3900, 0xF9C1, 0xF881, 0x3840,
            0x2800, 0xE8C1, 0xE981, 0x2940, 0xEB01, 0x2BC0, 0x2A80, 0xEA41,
            0xEE01, 0x2EC0, 0x2F80, 0xEF41, 0x2D00, 0xEDC1, 0xEC81, 0x2C40,
            0xE401, 0x24C0, 0x2580, 0xE541, 0x2700, 0xE7C1, 0xE681, 0x2640,
            0x2200, 0xE2C1, 0xE381, 0x2340, 0xE101, 0x21C0, 0x2080, 0xE041,
            0xA001, 0x60C0, 0x6180, 0xA141, 0x6300, 0xA3C1, 0xA281, 0x6240,
            0x6600, 0xA6C1, 0xA781, 0x6740, 0xA501, 0x65C0, 0x6480, 0xA441,
            0x6C00, 0xACC1, 0xAD81, 0x6D40, 0xAF01, 0x6FC0, 0x6E80, 0xAE41,
            0xAA01, 0x6AC0, 0x6B80, 0xAB41, 0x6900, 0xA9C1, 0xA881, 0x6840,
            0x7800, 0xB8C1, 0xB981, 0x7940, 0xBB01, 0x7BC0, 0x7A80, 0xBA41,
            0xBE01, 0x7EC0, 0x7F80, 0xBF41, 0x7D00, 0xBDC1, 0xBC81, 0x7C40,
            0xB401, 0x74C0, 0x7580, 0xB541, 0x7700, 0xB7C1, 0xB681, 0x7640,
            0x7200, 0xB2C1, 0xB381, 0x7340, 0xB101, 0x71C0, 0x7080, 0xB041,
            0x5000, 0x90C1, 0x9181, 0x5140, 0x9301, 0x53C0, 0x5280, 0x9241,
            0x9601, 0x56C0, 0x5780, 0x9741, 0x5500, 0x95C1, 0x9481, 0x5440,
            0x9C01, 0x5CC0, 0x5D80, 0x9D41, 0x5F00, 0x9FC1, 0x9E81, 0x5E40,
            0x5A00, 0x9AC1, 0x9B81, 0x5B40, 0x9901, 0x59C0, 0x5880, 0x9841,
            0x8801, 0x48C0, 0x4980, 0x8941, 0x4B00, 0x8BC1, 0x8A81, 0x4A40,
            0x4E00, 0x8EC1, 0x8F81, 0x4F40, 0x8D01, 0x4DC0, 0x4C80, 0x8C41,
            0x4400, 0x84C1, 0x8581, 0x4540, 0x8701, 0x47C0, 0x4680, 0x8641,
            0x8201, 0x42C0, 0x4380, 0x8341, 0x4100, 0x81C1, 0x8081, 0x4040
    };

    private static Vector<String> hexFields = new Vector<>();

    static {
        hexFields.add("sync");
        hexFields.add("src");
        hexFields.add("dst");
    }

    /**
     * Generate an HTML representation of the given message
     *
     * @param message The message
     * @return String with the HTML corresponding to given message
     */
    public static String getAsHtml(IMCMessage message) {
        return "<html><h1>" + message.getAbbrev() + "</h1>" +
                getAsInnerHtml(message.getHeader()) + "<br/>" +
                getAsInnerHtml(message) +
                "</html>";
    }

    /**
     * Same as <code>updateMessage(msg, IMCDefinition.getInstance()</code>
     *
     * @param msg The message to be updated to latest IMCDefinition
     * @see #updateMessage(IMCMessage, IMCDefinition)
     */
    public static void updateMessage(IMCMessage msg) {
        updateMessage(msg, IMCDefinition.getInstance());
    }

    /**
     * Update the message to a newer IMCDefinition
     *
     * @param msg    The message to be updated
     * @param target Target IMC definitions. The version should be newer.
     */
    public static void updateMessage(IMCMessage msg, IMCDefinition target) {
        switch (msg.getMgid()) {
            case EulerAngles.ID_STATIC:
                if (msg.getTypeOf("roll") == null && target.getType(EulerAngles.ID_STATIC).getFieldType("roll") != null) {
                    msg.setValue("roll", msg.getDouble("phi"));
                    msg.setValue("pitch", msg.getDouble("theta"));
                    msg.setValue("yaw", msg.getDouble("psi"));
                    msg.setValue("roll_magnetic", msg.getDouble("phi_magnetic"));
                    msg.setValue("pitch_magnetic", msg.getDouble("theta_magnetic"));
                    msg.setValue("yaw_magnetic", msg.getDouble("psi_magnetic"));
                }
                break;
            case LblConfig.ID_STATIC:
                if (msg.getTypeOf("beacon0") != null
                        && target.getType(LblConfig.ID_STATIC).getFieldType("beacons") != null) {
                    Vector<IMCMessage> beacons = new Vector<>();
                    for (String id : new String[]{"beacon0", "beacon1", "beacon2", "beacon3", "beacon4", "beacon5"}) {
                        IMCMessage m = (msg.getMessage(id));
                        if (m != null)
                            beacons.add(m);
                    }
                    msg.setValue("beacons", beacons);
                }
                break;
            case SonarData.ID_STATIC:
                if (msg.getTypeOf("min_range") == null
                        && target.getType(SonarData.ID_STATIC).getFieldType("min_range") != null) {
                    msg.setValue("min_range", msg.getDouble("range"));
                    msg.setValue("max_range", msg.getDouble("range"));
                }
            case FollowTrajectory.ID_STATIC:
            case FollowPath.ID_STATIC:
            case VehicleFormation.ID_STATIC:
                if (msg.getTypeOf("points").equals("message")
                        && target.getType(FollowPath.ID_STATIC).getFieldType("points") == IMCFieldType.TYPE_MESSAGELIST) {
                    Vector<IMCMessage> points = new Vector<>();
                    IMCMessage curr = msg.getMessage("points");
                    while (curr != null) {
                        IMCMessage prev = curr;
                        points.add(curr);
                        curr = curr.getMessage("point");
                        prev.setType(target.getType(prev.getMgid()));
                    }
                    msg.setValue("points", points);
                }
                break;
            case PlanSpecification.ID_STATIC:
                PlanSpecificationAdapter plan = new PlanSpecificationAdapter(msg);
                try {
                    msg.copyFrom(plan.getData(target));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        if (target.getType(msg.getMgid()) != null) {
            msg.setType(target.getType(msg.getMgid()));
        }
    }

    private static String getAsInnerHtml(IMCMessage msg) {
        if (msg == null)
            return "null";

        StringBuilder ret = new StringBuilder("<table border=1><tr bgcolor='#CCCCEE'><th>" + msg.getAbbrev() + "</th><th>" + msg.getFieldNames().length + " fields</th></tr>");
        if (msg.getAbbrev() == null)
            ret = new StringBuilder("<table border=1 width=100%><tr bgcolor='#CCEECC'><th>Header</th><th>" + msg.getFieldNames().length + " fields</th></tr>");

        for (String fieldName : msg.getFieldNames()) {
            StringBuilder value = new StringBuilder(msg.getString(fieldName));
            if (msg.getAbbrev() == null && fieldName.equals("timestamp")) {
                SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss.SSS ");
                dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
                value = new StringBuilder(dateFormatUTC.format(new Date(msg.getTimestampMillis())) + "UTC");
            } else if (msg.getAbbrev() == null && hexFields.contains(fieldName)) {
                value.append("  [0x").append(Long.toHexString(msg.getLong(fieldName)).toUpperCase()).append("]");
            }

            if (msg.getTypeOf(fieldName).equalsIgnoreCase("message") && msg.getValue(fieldName) != null)
                value = new StringBuilder(getAsInnerHtml(msg.getMessage(fieldName)));

            else if (msg.getTypeOf(fieldName).equalsIgnoreCase("message-list") && msg.getValue(fieldName) != null) {
                value = new StringBuilder("<table><tr>");
                for (IMCMessage m : msg.getMessageList(fieldName))
                    value.append("<td>").append(getAsInnerHtml(m)).append("</td>");

                value.append("</tr></table>");
            }

            ret.append("<tr><td align=center width=225>").append(fieldName).append("</td><td width=225>").append(value).append("</td></tr>");
        }
        return ret + "</table>";
    }

    public static String getBundledDefinitions() {
        java.io.InputStream xmlStream = IMCUtil.class.getResourceAsStream("/IMC.xml");
        java.io.InputStreamReader isreader = new java.io.InputStreamReader(xmlStream);
        java.io.BufferedReader reader = new java.io.BufferedReader(isreader);
        StringBuilder builder = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null)
                builder.append(line).append("\n");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return builder.toString();
    }

    private static byte[] computeMD5(InputStream defStream) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] extra = new byte[50000];
            int ret;
            for (; ; ) {
                ret = defStream.read(extra);
                if (ret != -1) {
                    byte[] extra1 = new byte[ret];
                    System.arraycopy(extra, 0, extra1, 0, ret);
                    baos.write(extra1);
                    baos.flush();
                } else {
                    break;
                }
            }

            md.update(baos.toByteArray());
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String computeMD5String(InputStream defStream) {
        byte[] md5Array = computeMD5(defStream);
        if (md5Array != null) {
            BigInteger bi = new BigInteger(1, md5Array);
            return bi.toString(16);
        } else {
            return "";
        }
    }
}
