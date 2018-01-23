package pt.lsts.imc;

public class DissolvedOrganicMatter extends IMCMessage {
    public static final int ID_STATIC = 903;


    public enum TYPE {
        COLORED(0),
        FLUORESCENT(1);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public DissolvedOrganicMatter() {
        super(ID_STATIC);
    }

    public DissolvedOrganicMatter(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DissolvedOrganicMatter(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DissolvedOrganicMatter create(Object... values) {
        DissolvedOrganicMatter m = new DissolvedOrganicMatter();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DissolvedOrganicMatter clone(IMCMessage msg) throws Exception {
        DissolvedOrganicMatter m = new DissolvedOrganicMatter();
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

    public double getValue() {
        return getDouble("value");
    }

    public DissolvedOrganicMatter setValue(double value) {
        values.put("value", value);
        return this;
    }

    public TYPE getType() {
        try {
            TYPE o = TYPE.valueOf(getMessageType().getFieldPossibleValues("type").get(getLong("type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTypeStr() {
        return getString("type");
    }

    public short getTypeVal() {
        return getShort("type");
    }

    public DissolvedOrganicMatter setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public DissolvedOrganicMatter setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public DissolvedOrganicMatter setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

}
