package pt.lsts.imc;

public class TrexAttribute extends IMCMessage {
    public static final int ID_STATIC = 656;


    public enum ATTR_TYPE {
        BOOL(1),
        INT(2),
        FLOAT(3),
        STRING(4),
        ENUM(5);

        protected long value;

        public long value() {
            return value;
        }

        ATTR_TYPE(long value) {
            this.value = value;
        }
    }

    public TrexAttribute() {
        super(ID_STATIC);
    }

    public TrexAttribute(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TrexAttribute(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrexAttribute create(Object... values) {
        TrexAttribute m = new TrexAttribute();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TrexAttribute clone(IMCMessage msg) throws Exception {
        TrexAttribute m = new TrexAttribute();
        if (msg == null)
            return m;
        if (msg.definitions != m.definitions) {
            msg = msg.cloneMessage();
            IMCUtil.updateMessage(msg, m.definitions);
        } else if (msg.getMgid() != m.getMgid()) {
            throw new Exception("incompatible types: " + msg.getAbbrev() + " and " + m.getAbbrev());
        }
        m.getHeader().values.putAll(msg.getHeader().values);
        m.values.putAll(msg.values);
        return m;
    }

    public String getName() {
        return getString("name");
    }

    public TrexAttribute setName(String name) {
        values.put("name", name);
        return this;
    }

    public ATTR_TYPE getAttrType() {
        try {
            ATTR_TYPE o = ATTR_TYPE.valueOf(getMessageType().getFieldPossibleValues("attr_type").get(getLong("attr_type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAttrTypeStr() {
        return getString("attr_type");
    }

    public short getAttrTypeVal() {
        return getShort("attr_type");
    }

    public TrexAttribute setAttrTypeStr(String attr_type) {
        setValue("attr_type", attr_type);
        return this;
    }

    public TrexAttribute setAttrTypeVal(ATTR_TYPE attr_type) {
        setValue("attr_type", attr_type);
        return this;
    }

    public TrexAttribute setAttrType(ATTR_TYPE attr_type) {
        values.put("attr_type", attr_type.value());
        return this;
    }

    public String getMin() {
        return getString("min");
    }

    public TrexAttribute setMin(String min) {
        values.put("min", min);
        return this;
    }

    public String getMax() {
        return getString("max");
    }

    public TrexAttribute setMax(String max) {
        values.put("max", max);
        return this;
    }

}
