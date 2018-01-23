package pt.lsts.imc;

public class CacheControl extends IMCMessage {
    public static final int ID_STATIC = 101;


    public enum OP {
        STORE(0),
        LOAD(1),
        CLEAR(2),
        COPY(3),
        COPY_COMPLETE(4);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public CacheControl() {
        super(ID_STATIC);
    }

    public CacheControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CacheControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CacheControl create(Object... values) {
        CacheControl m = new CacheControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CacheControl clone(IMCMessage msg) throws Exception {
        CacheControl m = new CacheControl();
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

    public CacheControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public CacheControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public CacheControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getSnapshot() {
        return getString("snapshot");
    }

    public CacheControl setSnapshot(String snapshot) {
        values.put("snapshot", snapshot);
        return this;
    }

    public IMCMessage getMessage() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getMessage(Class<T> clazz) throws Exception {
        return getMessage(clazz, "message");
    }

    public CacheControl setMessage(IMCMessage message) {
        values.put("message", message);
        return this;
    }

}
