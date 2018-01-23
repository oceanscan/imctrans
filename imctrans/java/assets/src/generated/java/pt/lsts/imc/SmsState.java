package pt.lsts.imc;

public class SmsState extends IMCMessage {
    public static final int ID_STATIC = 159;


    public enum STATE {
        ACCEPTED(0),
        REJECTED(1),
        INTERRUPTED(2),
        COMPLETED(3),
        IDLE(4),
        TRANSMITTING(5),
        RECEIVING(6);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public SmsState() {
        super(ID_STATIC);
    }

    public SmsState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SmsState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SmsState create(Object... values) {
        SmsState m = new SmsState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SmsState clone(IMCMessage msg) throws Exception {
        SmsState m = new SmsState();
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

    public long getSeq() {
        return getLong("seq");
    }

    public SmsState setSeq(long seq) {
        values.put("seq", seq);
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

    public SmsState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public SmsState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public SmsState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

    public String getError() {
        return getString("error");
    }

    public SmsState setError(String error) {
        values.put("error", error);
        return this;
    }

}
