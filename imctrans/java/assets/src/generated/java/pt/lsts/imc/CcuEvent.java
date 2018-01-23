package pt.lsts.imc;

public class CcuEvent extends IMCMessage {
    public static final int ID_STATIC = 606;


    public enum TYPE {
        LOG_ENTRY(1),
        PLAN_ADDED(2),
        PLAN_REMOVED(3),
        PLAN_CHANGED(4),
        MAP_FEATURE_ADDED(5),
        MAP_FEATURE_REMOVED(6),
        MAP_FEATURE_CHANGED(7),
        TELEOPERATION_STARTED(8),
        TELEOPERATION_ENDED(9);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public CcuEvent() {
        super(ID_STATIC);
    }

    public CcuEvent(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CcuEvent(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CcuEvent create(Object... values) {
        CcuEvent m = new CcuEvent();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CcuEvent clone(IMCMessage msg) throws Exception {
        CcuEvent m = new CcuEvent();
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

    public CcuEvent setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public CcuEvent setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public CcuEvent setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public String getId() {
        return getString("id");
    }

    public CcuEvent setId(String id) {
        values.put("id", id);
        return this;
    }

    public IMCMessage getArg() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getArg(Class<T> clazz) throws Exception {
        return getMessage(clazz, "arg");
    }

    public CcuEvent setArg(IMCMessage arg) {
        values.put("arg", arg);
        return this;
    }

}
