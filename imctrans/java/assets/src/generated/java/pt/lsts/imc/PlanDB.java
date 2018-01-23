package pt.lsts.imc;

public class PlanDB extends IMCMessage {
    public static final int ID_STATIC = 556;


    public enum TYPE {
        REQUEST(0),
        SUCCESS(1),
        FAILURE(2),
        IN_PROGRESS(3);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public enum OP {
        SET(0),
        DEL(1),
        GET(2),
        GET_INFO(3),
        CLEAR(4),
        GET_STATE(5),
        GET_DSTATE(6),
        BOOT(7);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public PlanDB() {
        super(ID_STATIC);
    }

    public PlanDB(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanDB(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanDB create(Object... values) {
        PlanDB m = new PlanDB();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanDB clone(IMCMessage msg) throws Exception {
        PlanDB m = new PlanDB();
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

    public PlanDB setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public PlanDB setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public PlanDB setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public OP getOp() {
        try {
            OP o = OP.valueOf(getMessageType().getFieldPossibleValues("op").get(getLong("op")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getOpStr() {
        return getString("op");
    }

    public short getOpVal() {
        return getShort("op");
    }

    public PlanDB setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public PlanDB setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public PlanDB setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public int getRequestId() {
        return getInteger("request_id");
    }

    public PlanDB setRequestId(int request_id) {
        values.put("request_id", request_id);
        return this;
    }

    public String getPlanId() {
        return getString("plan_id");
    }

    public PlanDB setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public IMCMessage getArg() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getArg(Class<T> clazz) throws Exception {
        return getMessage(clazz, "arg");
    }

    public PlanDB setArg(IMCMessage arg) {
        values.put("arg", arg);
        return this;
    }

    public String getInfo() {
        return getString("info");
    }

    public PlanDB setInfo(String info) {
        values.put("info", info);
        return this;
    }

}
