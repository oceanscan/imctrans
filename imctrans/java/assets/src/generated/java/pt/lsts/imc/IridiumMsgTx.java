package pt.lsts.imc;

public class IridiumMsgTx extends IMCMessage {
    public static final int ID_STATIC = 171;


    public IridiumMsgTx() {
        super(ID_STATIC);
    }

    public IridiumMsgTx(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public IridiumMsgTx(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IridiumMsgTx create(Object... values) {
        IridiumMsgTx m = new IridiumMsgTx();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static IridiumMsgTx clone(IMCMessage msg) throws Exception {
        IridiumMsgTx m = new IridiumMsgTx();
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

    public IridiumMsgTx setReqId(int req_id) {
        values.put("req_id", req_id);
        return this;
    }

    public int getTtl() {
        return getInteger("ttl");
    }

    public IridiumMsgTx setTtl(int ttl) {
        values.put("ttl", ttl);
        return this;
    }

    public String getDestination() {
        return getString("destination");
    }

    public IridiumMsgTx setDestination(String destination) {
        values.put("destination", destination);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public IridiumMsgTx setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
