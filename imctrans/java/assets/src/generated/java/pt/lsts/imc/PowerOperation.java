package pt.lsts.imc;

public class PowerOperation extends IMCMessage {
    public static final int ID_STATIC = 308;


    public enum OP {
        PWR_DOWN(0),
        PWR_DOWN_IP(1),
        PWR_DOWN_ABORTED(2),
        SCHED_PWR_DOWN(3),
        PWR_UP(4),
        PWR_UP_IP(5),
        SCHED_PWR_UP(6);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public PowerOperation() {
        super(ID_STATIC);
    }

    public PowerOperation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PowerOperation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PowerOperation create(Object... values) {
        PowerOperation m = new PowerOperation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PowerOperation clone(IMCMessage msg) throws Exception {
        PowerOperation m = new PowerOperation();
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

    public PowerOperation setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public PowerOperation setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public PowerOperation setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getTimeRemain() {
        return getDouble("time_remain");
    }

    public PowerOperation setTimeRemain(double time_remain) {
        values.put("time_remain", time_remain);
        return this;
    }

    public double getSchedTime() {
        return getDouble("sched_time");
    }

    public PowerOperation setSchedTime(double sched_time) {
        values.put("sched_time", sched_time);
        return this;
    }

}
