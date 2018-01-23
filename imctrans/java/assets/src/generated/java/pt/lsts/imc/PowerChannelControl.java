package pt.lsts.imc;

public class PowerChannelControl extends IMCMessage {
    public static final int ID_STATIC = 309;


    public enum OP {
        TURN_OFF(0),
        TURN_ON(1),
        TOGGLE(2),
        SCHED_ON(3),
        SCHED_OFF(4),
        SCHED_RESET(5),
        SAVE(6);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public PowerChannelControl() {
        super(ID_STATIC);
    }

    public PowerChannelControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PowerChannelControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PowerChannelControl create(Object... values) {
        PowerChannelControl m = new PowerChannelControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PowerChannelControl clone(IMCMessage msg) throws Exception {
        PowerChannelControl m = new PowerChannelControl();
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

    public String getName() {
        return getString("name");
    }

    public PowerChannelControl setName(String name) {
        values.put("name", name);
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

    public PowerChannelControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public PowerChannelControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public PowerChannelControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getSchedTime() {
        return getDouble("sched_time");
    }

    public PowerChannelControl setSchedTime(double sched_time) {
        values.put("sched_time", sched_time);
        return this;
    }

}
