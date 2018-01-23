package pt.lsts.imc;

public class ManeuverControlState extends IMCMessage {
    public static final int ID_STATIC = 470;


    public enum STATE {
        EXECUTING(0),
        DONE(1),
        ERROR(2),
        STOPPED(3);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public ManeuverControlState() {
        super(ID_STATIC);
    }

    public ManeuverControlState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ManeuverControlState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ManeuverControlState create(Object... values) {
        ManeuverControlState m = new ManeuverControlState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ManeuverControlState clone(IMCMessage msg) throws Exception {
        ManeuverControlState m = new ManeuverControlState();
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

    public ManeuverControlState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public ManeuverControlState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public ManeuverControlState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

    public int getEta() {
        return getInteger("eta");
    }

    public ManeuverControlState setEta(int eta) {
        values.put("eta", eta);
        return this;
    }

    public String getInfo() {
        return getString("info");
    }

    public ManeuverControlState setInfo(String info) {
        values.put("info", info);
        return this;
    }

}
