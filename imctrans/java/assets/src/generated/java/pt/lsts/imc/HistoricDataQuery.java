package pt.lsts.imc;

public class HistoricDataQuery extends IMCMessage {
    public static final int ID_STATIC = 187;


    public enum TYPE {
        QUERY(1),
        REPLY(2),
        CLEAR(3);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public HistoricDataQuery() {
        super(ID_STATIC);
    }

    public HistoricDataQuery(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public HistoricDataQuery(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HistoricDataQuery create(Object... values) {
        HistoricDataQuery m = new HistoricDataQuery();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static HistoricDataQuery clone(IMCMessage msg) throws Exception {
        HistoricDataQuery m = new HistoricDataQuery();
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

    public int getReqId() {
        return getInteger("req_id");
    }

    public HistoricDataQuery setReqId(int req_id) {
        values.put("req_id", req_id);
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

    public HistoricDataQuery setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public HistoricDataQuery setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public HistoricDataQuery setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public int getMaxSize() {
        return getInteger("max_size");
    }

    public HistoricDataQuery setMaxSize(int max_size) {
        values.put("max_size", max_size);
        return this;
    }

    public HistoricData getData() {
        return getMessageOrNull(HistoricData.class, "HistoricData");
    }

    public <T extends IMCMessage> T getData(Class<T> clazz) throws Exception {
        return getMessage(clazz, "data");
    }

    public HistoricDataQuery setData(HistoricData data) {
        values.put("data", data);
        return this;
    }

}
