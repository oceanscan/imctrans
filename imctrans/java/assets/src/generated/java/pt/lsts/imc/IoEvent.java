package pt.lsts.imc;

public class IoEvent extends IMCMessage {
    public static final int ID_STATIC = 813;


    public enum TYPE {
        INPUT(1),
        INPUT_ERROR(2);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public IoEvent() {
        super(ID_STATIC);
    }

    public IoEvent(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public IoEvent(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IoEvent create(Object... values) {
        IoEvent m = new IoEvent();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static IoEvent clone(IMCMessage msg) throws Exception {
        IoEvent m = new IoEvent();
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

    public IoEvent setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public IoEvent setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public IoEvent setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public String getError() {
        return getString("error");
    }

    public IoEvent setError(String error) {
        values.put("error", error);
        return this;
    }

}
