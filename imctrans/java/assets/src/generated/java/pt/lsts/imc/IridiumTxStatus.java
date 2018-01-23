package pt.lsts.imc;

public class IridiumTxStatus extends IMCMessage {
    public static final int ID_STATIC = 172;


    public enum STATUS {
        OK(1),
        ERROR(2),
        QUEUED(3),
        TRANSMIT(4),
        EXPIRED(5);

        protected long value;

        public long value() {
            return value;
        }

        STATUS(long value) {
            this.value = value;
        }
    }

    public IridiumTxStatus() {
        super(ID_STATIC);
    }

    public IridiumTxStatus(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public IridiumTxStatus(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IridiumTxStatus create(Object... values) {
        IridiumTxStatus m = new IridiumTxStatus();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static IridiumTxStatus clone(IMCMessage msg) throws Exception {
        IridiumTxStatus m = new IridiumTxStatus();
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

    public int getReqId() {
        return getInteger("req_id");
    }

    public IridiumTxStatus setReqId(int req_id) {
        values.put("req_id", req_id);
        return this;
    }

    public STATUS getStatus() {
        try {
            STATUS o = STATUS.valueOf(getMessageType().getFieldPossibleValues("status").get(getLong("status")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getStatusStr() {
        return getString("status");
    }

    public short getStatusVal() {
        return getShort("status");
    }

    public IridiumTxStatus setStatusStr(String status) {
        setValue("status", status);
        return this;
    }

    public IridiumTxStatus setStatusVal(STATUS status) {
        setValue("status", status);
        return this;
    }

    public IridiumTxStatus setStatus(STATUS status) {
        values.put("status", status.value());
        return this;
    }

    public String getText() {
        return getString("text");
    }

    public IridiumTxStatus setText(String text) {
        values.put("text", text);
        return this;
    }

}
