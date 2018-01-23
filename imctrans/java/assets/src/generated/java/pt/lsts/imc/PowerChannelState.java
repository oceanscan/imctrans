package pt.lsts.imc;

public class PowerChannelState extends IMCMessage {
    public static final int ID_STATIC = 311;


    public enum STATE {
        OFF(0),
        ON(1);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public PowerChannelState() {
        super(ID_STATIC);
    }

    public PowerChannelState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PowerChannelState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PowerChannelState create(Object... values) {
        PowerChannelState m = new PowerChannelState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PowerChannelState clone(IMCMessage msg) throws Exception {
        PowerChannelState m = new PowerChannelState();
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

    public PowerChannelState setName(String name) {
        values.put("name", name);
        return this;
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

    public PowerChannelState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public PowerChannelState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public PowerChannelState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

}
