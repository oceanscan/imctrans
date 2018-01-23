package pt.lsts.imc;

public class AlignmentState extends IMCMessage {
    public static final int ID_STATIC = 361;


    public enum STATE {
        NOT_ALIGNED(0),
        ALIGNED(1),
        NOT_SUPPORTED(2),
        ALIGNING(3),
        WRONG_MEDIUM(4);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public AlignmentState() {
        super(ID_STATIC);
    }

    public AlignmentState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AlignmentState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AlignmentState create(Object... values) {
        AlignmentState m = new AlignmentState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AlignmentState clone(IMCMessage msg) throws Exception {
        AlignmentState m = new AlignmentState();
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

    public AlignmentState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public AlignmentState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public AlignmentState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

}
