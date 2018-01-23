package pt.lsts.imc;

public class PlanGeneration extends IMCMessage {
    public static final int ID_STATIC = 562;


    public enum CMD {
        GENERATE(0),
        EXECUTE(1);

        protected long value;

        public long value() {
            return value;
        }

        CMD(long value) {
            this.value = value;
        }
    }

    public enum OP {
        REQUEST(0),
        ERROR(1),
        SUCCESS(2);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public PlanGeneration() {
        super(ID_STATIC);
    }

    public PlanGeneration(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanGeneration(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanGeneration create(Object... values) {
        PlanGeneration m = new PlanGeneration();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanGeneration clone(IMCMessage msg) throws Exception {
        PlanGeneration m = new PlanGeneration();
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

    public CMD getCmd() {
        try {
            CMD o = CMD.valueOf(getMessageType().getFieldPossibleValues("cmd").get(getLong("cmd")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getCmdStr() {
        return getString("cmd");
    }

    public short getCmdVal() {
        return getShort("cmd");
    }

    public PlanGeneration setCmdStr(String cmd) {
        setValue("cmd", cmd);
        return this;
    }

    public PlanGeneration setCmdVal(CMD cmd) {
        setValue("cmd", cmd);
        return this;
    }

    public PlanGeneration setCmd(CMD cmd) {
        values.put("cmd", cmd.value());
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

    public PlanGeneration setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public PlanGeneration setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public PlanGeneration setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getPlanId() {
        return getString("plan_id");
    }

    public PlanGeneration setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getParams() {
        return getTupleList("params");
    }

    public PlanGeneration setParams(java.util.LinkedHashMap<String, ?> params) {
        return setParams(params);
    }

    public PlanGeneration setParams(String params) {
        values.put("params", params);
        return this;
    }

}
