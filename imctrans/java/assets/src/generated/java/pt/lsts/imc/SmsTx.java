package pt.lsts.imc;

public class SmsTx extends IMCMessage {
    public static final int ID_STATIC = 157;


    public SmsTx() {
        super(ID_STATIC);
    }

    public SmsTx(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SmsTx(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SmsTx create(Object... values) {
        SmsTx m = new SmsTx();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SmsTx clone(IMCMessage msg) throws Exception {
        SmsTx m = new SmsTx();
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

    public SmsTx setSeq(long seq) {
        values.put("seq", seq);
        return this;
    }

    public String getDestination() {
        return getString("destination");
    }

    public SmsTx setDestination(String destination) {
        values.put("destination", destination);
        return this;
    }

    public int getTimeout() {
        return getInteger("timeout");
    }

    public SmsTx setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public SmsTx setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
