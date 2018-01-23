package pt.lsts.imc;

public class LogBookEntry extends IMCMessage {
    public static final int ID_STATIC = 103;


    public enum TYPE {
        INFO(0),
        WARNING(1),
        ERROR(2),
        CRITICAL(3),
        DEBUG(4);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public LogBookEntry() {
        super(ID_STATIC);
    }

    public LogBookEntry(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LogBookEntry(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LogBookEntry create(Object... values) {
        LogBookEntry m = new LogBookEntry();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LogBookEntry clone(IMCMessage msg) throws Exception {
        LogBookEntry m = new LogBookEntry();
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

    public LogBookEntry setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public LogBookEntry setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public LogBookEntry setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public double getHtime() {
        return getDouble("htime");
    }

    public LogBookEntry setHtime(double htime) {
        values.put("htime", htime);
        return this;
    }

    public String getContext() {
        return getString("context");
    }

    public LogBookEntry setContext(String context) {
        values.put("context", context);
        return this;
    }

    public String getText() {
        return getString("text");
    }

    public LogBookEntry setText(String text) {
        values.put("text", text);
        return this;
    }

}
