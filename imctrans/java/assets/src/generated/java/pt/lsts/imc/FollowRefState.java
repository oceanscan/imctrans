package pt.lsts.imc;

public class FollowRefState extends IMCMessage {
    public static final int ID_STATIC = 480;

public static final short PROX_FAR = 0x01;
public static final short PROX_XY_NEAR = 0x02;
public static final short PROX_Z_NEAR = 0x04;
public static final short PROX_XY_UNREACHABLE = 0x08;
public static final short PROX_Z_UNREACHABLE = 0x10;

    public enum STATE {
        WAIT(1),
        GOTO(2),
        LOITER(3),
        HOVER(4),
        ELEVATOR(5),
        TIMEOUT(6);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public FollowRefState() {
        super(ID_STATIC);
    }

    public FollowRefState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FollowRefState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FollowRefState create(Object... values) {
        FollowRefState m = new FollowRefState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FollowRefState clone(IMCMessage msg) throws Exception {
        FollowRefState m = new FollowRefState();
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

    public int getControlSrc() {
        return getInteger("control_src");
    }

    public FollowRefState setControlSrc(int control_src) {
        values.put("control_src", control_src);
        return this;
    }

    public short getControlEnt() {
        return getShort("control_ent");
    }

    public FollowRefState setControlEnt(short control_ent) {
        values.put("control_ent", control_ent);
        return this;
    }

    public Reference getReference() {
        return getMessageOrNull(Reference.class, "Reference");
    }

    public <T extends IMCMessage> T getReference(Class<T> clazz) throws Exception {
        return getMessage(clazz, "reference");
    }

    public FollowRefState setReference(Reference reference) {
        values.put("reference", reference);
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

    public FollowRefState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public FollowRefState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public FollowRefState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

    public short getProximity() {
        return getShort("proximity");
    }

    public FollowRefState setProximity(short proximity) {
        values.put("proximity", proximity);
        return this;
    }

}
