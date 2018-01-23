package pt.lsts.imc;

public class AcousticOperation extends IMCMessage {
    public static final int ID_STATIC = 211;


    public enum OP {
        ABORT(0),
        ABORT_IP(1),
        ABORT_TIMEOUT(2),
        ABORT_ACKED(3),
        RANGE(4),
        RANGE_IP(5),
        RANGE_TIMEOUT(6),
        RANGE_RECVED(7),
        BUSY(8),
        UNSUPPORTED(9),
        NO_TXD(10),
        MSG(11),
        MSG_QUEUED(12),
        MSG_IP(13),
        MSG_DONE(14),
        MSG_FAILURE(15),
        MSG_SHORT(16);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public AcousticOperation() {
        super(ID_STATIC);
    }

    public AcousticOperation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AcousticOperation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AcousticOperation create(Object... values) {
        AcousticOperation m = new AcousticOperation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AcousticOperation clone(IMCMessage msg) throws Exception {
        AcousticOperation m = new AcousticOperation();
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

    public AcousticOperation setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public AcousticOperation setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public AcousticOperation setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getSystem() {
        return getString("system");
    }

    public AcousticOperation setSystem(String system) {
        values.put("system", system);
        return this;
    }

    public double getRange() {
        return getDouble("range");
    }

    public AcousticOperation setRange(double range) {
        values.put("range", range);
        return this;
    }

    public IMCMessage getMsg() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getMsg(Class<T> clazz) throws Exception {
        return getMessage(clazz, "msg");
    }

    public AcousticOperation setMsg(IMCMessage msg) {
        values.put("msg", msg);
        return this;
    }

}
