package pt.lsts.imc;

public class LblBeacon extends IMCMessage {
    public static final int ID_STATIC = 202;


    public LblBeacon() {
        super(ID_STATIC);
    }

    public LblBeacon(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LblBeacon(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LblBeacon create(Object... values) {
        LblBeacon m = new LblBeacon();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LblBeacon clone(IMCMessage msg) throws Exception {
        LblBeacon m = new LblBeacon();
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

    public String getBeacon() {
        return getString("beacon");
    }

    public LblBeacon setBeacon(String beacon) {
        values.put("beacon", beacon);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public LblBeacon setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public LblBeacon setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getDepth() {
        return getDouble("depth");
    }

    public LblBeacon setDepth(double depth) {
        values.put("depth", depth);
        return this;
    }

    public short getQueryChannel() {
        return getShort("query_channel");
    }

    public LblBeacon setQueryChannel(short query_channel) {
        values.put("query_channel", query_channel);
        return this;
    }

    public short getReplyChannel() {
        return getShort("reply_channel");
    }

    public LblBeacon setReplyChannel(short reply_channel) {
        values.put("reply_channel", reply_channel);
        return this;
    }

    public short getTransponderDelay() {
        return getShort("transponder_delay");
    }

    public LblBeacon setTransponderDelay(short transponder_delay) {
        values.put("transponder_delay", transponder_delay);
        return this;
    }

}
