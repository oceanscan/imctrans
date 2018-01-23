package pt.lsts.imc;

public class EmergencyControl extends IMCMessage {
    public static final int ID_STATIC = 554;


    public enum COMMAND {
        ENABLE(0),
        DISABLE(1),
        START(2),
        STOP(3),
        QUERY(4),
        SET_PLAN(5);

        protected long value;

        public long value() {
            return value;
        }

        COMMAND(long value) {
            this.value = value;
        }
    }

    public EmergencyControl() {
        super(ID_STATIC);
    }

    public EmergencyControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EmergencyControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EmergencyControl create(Object... values) {
        EmergencyControl m = new EmergencyControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EmergencyControl clone(IMCMessage msg) throws Exception {
        EmergencyControl m = new EmergencyControl();
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

    public COMMAND getCommand() {
        try {
            COMMAND o = COMMAND.valueOf(getMessageType().getFieldPossibleValues("command").get(getLong("command")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getCommandStr() {
        return getString("command");
    }

    public short getCommandVal() {
        return getShort("command");
    }

    public EmergencyControl setCommandStr(String command) {
        setValue("command", command);
        return this;
    }

    public EmergencyControl setCommandVal(COMMAND command) {
        setValue("command", command);
        return this;
    }

    public EmergencyControl setCommand(COMMAND command) {
        values.put("command", command.value());
        return this;
    }

    public PlanSpecification getPlan() {
        return getMessageOrNull(PlanSpecification.class, "PlanSpecification");
    }

    public <T extends IMCMessage> T getPlan(Class<T> clazz) throws Exception {
        return getMessage(clazz, "plan");
    }

    public EmergencyControl setPlan(PlanSpecification plan) {
        values.put("plan", plan);
        return this;
    }

}
