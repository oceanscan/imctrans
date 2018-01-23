package pt.lsts.imc;

public class HistoricEvent extends IMCMessage {
    public static final int ID_STATIC = 110;


    public enum TYPE {
        INFO(0),
        ERROR(1);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public HistoricEvent() {
        super(ID_STATIC);
    }

    public HistoricEvent(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public HistoricEvent(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HistoricEvent create(Object... values) {
        HistoricEvent m = new HistoricEvent();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static HistoricEvent clone(IMCMessage msg) throws Exception {
        HistoricEvent m = new HistoricEvent();
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

    public String getText() {
        return getString("text");
    }

    public HistoricEvent setText(String text) {
        values.put("text", text);
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

    public HistoricEvent setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public HistoricEvent setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public HistoricEvent setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

}
