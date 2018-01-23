package pt.lsts.imc;

public class IridiumMsgTxExtended extends IMCMessage {
    public static final int ID_STATIC = 2005;


    public IridiumMsgTxExtended() {
        super(ID_STATIC);
    }

    public IridiumMsgTxExtended(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public IridiumMsgTxExtended(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IridiumMsgTxExtended create(Object... values) {
        IridiumMsgTxExtended m = new IridiumMsgTxExtended();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static IridiumMsgTxExtended clone(IMCMessage msg) throws Exception {
        IridiumMsgTxExtended m = new IridiumMsgTxExtended();
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

    public IridiumMsgTxExtended setReqId(int req_id) {
        values.put("req_id", req_id);
        return this;
    }

    public int getTtl() {
        return getInteger("ttl");
    }

    public IridiumMsgTxExtended setTtl(int ttl) {
        values.put("ttl", ttl);
        return this;
    }

    public long getExpiration() {
        return getLong("expiration");
    }

    public IridiumMsgTxExtended setExpiration(long expiration) {
        values.put("expiration", expiration);
        return this;
    }

    public String getDestination() {
        return getString("destination");
    }

    public IridiumMsgTxExtended setDestination(String destination) {
        values.put("destination", destination);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public IridiumMsgTxExtended setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
