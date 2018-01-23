package pt.lsts.imc;

public class EntityState extends IMCMessage {
    public static final int ID_STATIC = 1;

public static final short EFLA_HUMAN_INTERVENTION = 0x01;

    public enum STATE {
        BOOT(0),
        NORMAL(1),
        FAULT(2),
        ERROR(3),
        FAILURE(4);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public EntityState() {
        super(ID_STATIC);
    }

    public EntityState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EntityState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityState create(Object... values) {
        EntityState m = new EntityState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EntityState clone(IMCMessage msg) throws Exception {
        EntityState m = new EntityState();
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

    public EntityState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public EntityState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public EntityState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public EntityState setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public String getDescription() {
        return getString("description");
    }

    public EntityState setDescription(String description) {
        values.put("description", description);
        return this;
    }

}
