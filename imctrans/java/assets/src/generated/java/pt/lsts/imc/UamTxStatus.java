package pt.lsts.imc;

public class UamTxStatus extends IMCMessage {
    public static final int ID_STATIC = 816;


    public enum VALUE {
        DONE(0),
        FAILED(1),
        CANCELED(2),
        BUSY(3),
        INV_ADDR(4),
        IP(5),
        UNSUPPORTED(6),
        INV_SIZE(7);

        protected long value;

        public long value() {
            return value;
        }

        VALUE(long value) {
            this.value = value;
        }
    }

    public UamTxStatus() {
        super(ID_STATIC);
    }

    public UamTxStatus(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UamTxStatus(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UamTxStatus create(Object... values) {
        UamTxStatus m = new UamTxStatus();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UamTxStatus clone(IMCMessage msg) throws Exception {
        UamTxStatus m = new UamTxStatus();
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

    public int getSeq() {
        return getInteger("seq");
    }

    public UamTxStatus setSeq(int seq) {
        values.put("seq", seq);
        return this;
    }

    public VALUE getValue() {
        try {
            VALUE o = VALUE.valueOf(getMessageType().getFieldPossibleValues("value").get(getLong("value")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getValueStr() {
        return getString("value");
    }

    public short getValueVal() {
        return getShort("value");
    }

    public UamTxStatus setValueStr(String value) {
        setValue("value", value);
        return this;
    }

    public UamTxStatus setValueVal(VALUE value) {
        setValue("value", value);
        return this;
    }

    public UamTxStatus setValue(VALUE value) {
        values.put("value", value.value());
        return this;
    }

    public String getError() {
        return getString("error");
    }

    public UamTxStatus setError(String error) {
        values.put("error", error);
        return this;
    }

}
