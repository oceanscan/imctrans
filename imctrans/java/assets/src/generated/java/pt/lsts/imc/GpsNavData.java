package pt.lsts.imc;

public class GpsNavData extends IMCMessage {
    public static final int ID_STATIC = 280;


    public GpsNavData() {
        super(ID_STATIC);
    }

    public GpsNavData(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GpsNavData(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GpsNavData create(Object... values) {
        GpsNavData m = new GpsNavData();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GpsNavData clone(IMCMessage msg) throws Exception {
        GpsNavData m = new GpsNavData();
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

    public long getItow() {
        return getLong("itow");
    }

    public GpsNavData setItow(long itow) {
        values.put("itow", itow);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public GpsNavData setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public GpsNavData setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getHeightEll() {
        return getDouble("height_ell");
    }

    public GpsNavData setHeightEll(double height_ell) {
        values.put("height_ell", height_ell);
        return this;
    }

    public double getHeightSea() {
        return getDouble("height_sea");
    }

    public GpsNavData setHeightSea(double height_sea) {
        values.put("height_sea", height_sea);
        return this;
    }

    public double getHacc() {
        return getDouble("hacc");
    }

    public GpsNavData setHacc(double hacc) {
        values.put("hacc", hacc);
        return this;
    }

    public double getVacc() {
        return getDouble("vacc");
    }

    public GpsNavData setVacc(double vacc) {
        values.put("vacc", vacc);
        return this;
    }

    public double getVelN() {
        return getDouble("vel_n");
    }

    public GpsNavData setVelN(double vel_n) {
        values.put("vel_n", vel_n);
        return this;
    }

    public double getVelE() {
        return getDouble("vel_e");
    }

    public GpsNavData setVelE(double vel_e) {
        values.put("vel_e", vel_e);
        return this;
    }

    public double getVelD() {
        return getDouble("vel_d");
    }

    public GpsNavData setVelD(double vel_d) {
        values.put("vel_d", vel_d);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public GpsNavData setSpeed(double speed) {
        values.put("speed", speed);
        return this;
    }

    public double getGspeed() {
        return getDouble("gspeed");
    }

    public GpsNavData setGspeed(double gspeed) {
        values.put("gspeed", gspeed);
        return this;
    }

    public double getHeading() {
        return getDouble("heading");
    }

    public GpsNavData setHeading(double heading) {
        values.put("heading", heading);
        return this;
    }

    public double getSacc() {
        return getDouble("sacc");
    }

    public GpsNavData setSacc(double sacc) {
        values.put("sacc", sacc);
        return this;
    }

    public double getCacc() {
        return getDouble("cacc");
    }

    public GpsNavData setCacc(double cacc) {
        values.put("cacc", cacc);
        return this;
    }

}
