package pt.lsts.imc;

public class RemoteSensorInfo extends IMCMessage {
    public static final int ID_STATIC = 601;


    public RemoteSensorInfo() {
        super(ID_STATIC);
    }

    public RemoteSensorInfo(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RemoteSensorInfo(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RemoteSensorInfo create(Object... values) {
        RemoteSensorInfo m = new RemoteSensorInfo();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RemoteSensorInfo clone(IMCMessage msg) throws Exception {
        RemoteSensorInfo m = new RemoteSensorInfo();
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

    public String getId() {
        return getString("id");
    }

    public RemoteSensorInfo setId(String id) {
        values.put("id", id);
        return this;
    }

    public String getSensorClass() {
        return getString("sensor_class");
    }

    public RemoteSensorInfo setSensorClass(String sensor_class) {
        values.put("sensor_class", sensor_class);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public RemoteSensorInfo setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public RemoteSensorInfo setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getAlt() {
        return getDouble("alt");
    }

    public RemoteSensorInfo setAlt(double alt) {
        values.put("alt", alt);
        return this;
    }

    public double getHeading() {
        return getDouble("heading");
    }

    public RemoteSensorInfo setHeading(double heading) {
        values.put("heading", heading);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getData() {
        return getTupleList("data");
    }

    public RemoteSensorInfo setData(java.util.LinkedHashMap<String, ?> data) {
        return setData(data);
    }

    public RemoteSensorInfo setData(String data) {
        values.put("data", data);
        return this;
    }

}
