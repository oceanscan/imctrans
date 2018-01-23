package pt.lsts.imc;

public class EntityActivationState extends IMCMessage {
    public static final int ID_STATIC = 14;


    public enum STATE {
        INACTIVE(0),
        ACTIVE(1),
        ACT_IP(2),
        ACT_DONE(3),
        ACT_FAIL(4),
        DEACT_IP(5),
        DEACT_DONE(6),
        DEACT_FAIL(7);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public EntityActivationState() {
        super(ID_STATIC);
    }

    public EntityActivationState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EntityActivationState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityActivationState create(Object... values) {
        EntityActivationState m = new EntityActivationState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EntityActivationState clone(IMCMessage msg) throws Exception {
        EntityActivationState m = new EntityActivationState();
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

    public EntityActivationState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public EntityActivationState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public EntityActivationState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

    public String getError() {
        return getString("error");
    }

    public EntityActivationState setError(String error) {
        values.put("error", error);
        return this;
    }

}
