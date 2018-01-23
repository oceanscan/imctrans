package pt.lsts.imc;

public class MapPoint extends IMCMessage {
    public static final int ID_STATIC = 604;


    public MapPoint() {
        super(ID_STATIC);
    }

    public MapPoint(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public MapPoint(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MapPoint create(Object... values) {
        MapPoint m = new MapPoint();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static MapPoint clone(IMCMessage msg) throws Exception {
        MapPoint m = new MapPoint();
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

    public double getLat() {
        return getDouble("lat");
    }

    public MapPoint setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public MapPoint setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getAlt() {
        return getDouble("alt");
    }

    public MapPoint setAlt(double alt) {
        values.put("alt", alt);
        return this;
    }

}
