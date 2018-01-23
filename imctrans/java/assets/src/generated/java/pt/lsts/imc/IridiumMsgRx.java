package pt.lsts.imc;

public class IridiumMsgRx extends IMCMessage {
    public static final int ID_STATIC = 170;


    public IridiumMsgRx() {
        super(ID_STATIC);
    }

    public IridiumMsgRx(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public IridiumMsgRx(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IridiumMsgRx create(Object... values) {
        IridiumMsgRx m = new IridiumMsgRx();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static IridiumMsgRx clone(IMCMessage msg) throws Exception {
        IridiumMsgRx m = new IridiumMsgRx();
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

    public String getOrigin() {
        return getString("origin");
    }

    public IridiumMsgRx setOrigin(String origin) {
        values.put("origin", origin);
        return this;
    }

    public double getHtime() {
        return getDouble("htime");
    }

    public IridiumMsgRx setHtime(double htime) {
        values.put("htime", htime);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public IridiumMsgRx setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public IridiumMsgRx setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public IridiumMsgRx setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
