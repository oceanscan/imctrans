package pt.lsts.imc;

public class EmergencyControlState extends IMCMessage {
    public static final int ID_STATIC = 555;


    public enum STATE {
        NOT_CONFIGURED(0),
        DISABLED(1),
        ENABLED(2),
        ARMED(3),
        ACTIVE(4),
        STOPPING(5);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public EmergencyControlState() {
        super(ID_STATIC);
    }

    public EmergencyControlState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EmergencyControlState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EmergencyControlState create(Object... values) {
        EmergencyControlState m = new EmergencyControlState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EmergencyControlState clone(IMCMessage msg) throws Exception {
        EmergencyControlState m = new EmergencyControlState();
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

    public STATE getState() {
        try {
            STATE o = STATE.valueOf(getMessageType().getFieldPossibleValues("state").get(getLong("state")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getStateStr() {
        return getString("state");
    }

    public short getStateVal() {
        return getShort("state");
    }

    public EmergencyControlState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public EmergencyControlState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public EmergencyControlState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

    public String getPlanId() {
        return getString("plan_id");
    }

    public EmergencyControlState setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public short getCommLevel() {
        return getShort("comm_level");
    }

    public EmergencyControlState setCommLevel(short comm_level) {
        values.put("comm_level", comm_level);
        return this;
    }

}
