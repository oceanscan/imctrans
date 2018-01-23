package pt.lsts.imc;

public class PlanControl extends IMCMessage {
    public static final int ID_STATIC = 559;

public static final int FLG_CALIBRATE = 0x0001;
public static final int FLG_IGNORE_ERRORS = 0x0002;

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
        START(0),
        STOP(1),
        LOAD(2),
        GET(3);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public PlanControl() {
        super(ID_STATIC);
    }

    public PlanControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanControl create(Object... values) {
        PlanControl m = new PlanControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanControl clone(IMCMessage msg) throws Exception {
        PlanControl m = new PlanControl();
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

    public PlanControl setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public PlanControl setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public PlanControl setType(TYPE type) {
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

    public PlanControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public PlanControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public PlanControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public int getRequestId() {
        return getInteger("request_id");
    }

    public PlanControl setRequestId(int request_id) {
        values.put("request_id", request_id);
        return this;
    }

    public String getPlanId() {
        return getString("plan_id");
    }

    public PlanControl setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public int getFlags() {
        return getInteger("flags");
    }

    public PlanControl setFlags(int flags) {
        values.put("flags", flags);
        return this;
    }

    public IMCMessage getArg() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getArg(Class<T> clazz) throws Exception {
        return getMessage(clazz, "arg");
    }

    public PlanControl setArg(IMCMessage arg) {
        values.put("arg", arg);
        return this;
    }

    public String getInfo() {
        return getString("info");
    }

    public PlanControl setInfo(String info) {
        values.put("info", info);
        return this;
    }

}
