package pt.lsts.imc;

public class GetWorldCoordinates extends IMCMessage {
    public static final int ID_STATIC = 897;


    public enum TRACKING {
        FALSE(0),
        TRUE(1);

        protected long value;

        public long value() {
            return value;
        }

        TRACKING(long value) {
            this.value = value;
        }
    }

    public GetWorldCoordinates() {
        super(ID_STATIC);
    }

    public GetWorldCoordinates(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GetWorldCoordinates(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GetWorldCoordinates create(Object... values) {
        GetWorldCoordinates m = new GetWorldCoordinates();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GetWorldCoordinates clone(IMCMessage msg) throws Exception {
        GetWorldCoordinates m = new GetWorldCoordinates();
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

    public TRACKING getTracking() {
        try {
            TRACKING o = TRACKING.valueOf(getMessageType().getFieldPossibleValues("tracking").get(getLong("tracking")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTrackingStr() {
        return getString("tracking");
    }

    public short getTrackingVal() {
        return getShort("tracking");
    }

    public GetWorldCoordinates setTrackingStr(String tracking) {
        setValue("tracking", tracking);
        return this;
    }

    public GetWorldCoordinates setTrackingVal(TRACKING tracking) {
        setValue("tracking", tracking);
        return this;
    }

    public GetWorldCoordinates setTracking(TRACKING tracking) {
        values.put("tracking", tracking.value());
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public GetWorldCoordinates setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public GetWorldCoordinates setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public GetWorldCoordinates setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public GetWorldCoordinates setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public GetWorldCoordinates setZ(double z) {
        values.put("z", z);
        return this;
    }

}
