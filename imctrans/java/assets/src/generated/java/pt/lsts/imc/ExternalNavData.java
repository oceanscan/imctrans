package pt.lsts.imc;

public class ExternalNavData extends IMCMessage {
    public static final int ID_STATIC = 294;


    public enum TYPE {
        FULL(0),
        AHRS(1),
        POSREF(2);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public ExternalNavData() {
        super(ID_STATIC);
    }

    public ExternalNavData(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ExternalNavData(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExternalNavData create(Object... values) {
        ExternalNavData m = new ExternalNavData();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ExternalNavData clone(IMCMessage msg) throws Exception {
        ExternalNavData m = new ExternalNavData();
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

    public EstimatedState getState() {
        return getMessageOrNull(EstimatedState.class, "EstimatedState");
    }

    public <T extends IMCMessage> T getState(Class<T> clazz) throws Exception {
        return getMessage(clazz, "state");
    }

    public ExternalNavData setState(EstimatedState state) {
        values.put("state", state);
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

    public ExternalNavData setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public ExternalNavData setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public ExternalNavData setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

}
