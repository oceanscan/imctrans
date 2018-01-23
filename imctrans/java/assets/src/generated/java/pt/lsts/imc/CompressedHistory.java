package pt.lsts.imc;

public class CompressedHistory extends IMCMessage {
    public static final int ID_STATIC = 185;


    public CompressedHistory() {
        super(ID_STATIC);
    }

    public CompressedHistory(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CompressedHistory(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CompressedHistory create(Object... values) {
        CompressedHistory m = new CompressedHistory();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CompressedHistory clone(IMCMessage msg) throws Exception {
        CompressedHistory m = new CompressedHistory();
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

    public double getBaseLat() {
        return getDouble("base_lat");
    }

    public CompressedHistory setBaseLat(double base_lat) {
        values.put("base_lat", base_lat);
        return this;
    }

    public double getBaseLon() {
        return getDouble("base_lon");
    }

    public CompressedHistory setBaseLon(double base_lon) {
        values.put("base_lon", base_lon);
        return this;
    }

    public double getBaseTime() {
        return getDouble("base_time");
    }

    public CompressedHistory setBaseTime(double base_time) {
        values.put("base_time", base_time);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public CompressedHistory setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
