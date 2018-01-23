package pt.lsts.imc;

public class HistoricData extends IMCMessage {
    public static final int ID_STATIC = 184;


    public HistoricData() {
        super(ID_STATIC);
    }

    public HistoricData(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public HistoricData(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HistoricData create(Object... values) {
        HistoricData m = new HistoricData();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static HistoricData clone(IMCMessage msg) throws Exception {
        HistoricData m = new HistoricData();
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

    public HistoricData setBaseLat(double base_lat) {
        values.put("base_lat", base_lat);
        return this;
    }

    public double getBaseLon() {
        return getDouble("base_lon");
    }

    public HistoricData setBaseLon(double base_lon) {
        values.put("base_lon", base_lon);
        return this;
    }

    public double getBaseTime() {
        return getDouble("base_time");
    }

    public HistoricData setBaseTime(double base_time) {
        values.put("base_time", base_time);
        return this;
    }

    public java.util.Vector<RemoteData> getData() {
        return getMessageListOrNull("data", RemoteData.class);
    }

    public HistoricData setData(java.util.Collection<RemoteData> data) {
        values.put("data", data);
        return this;
    }

}
