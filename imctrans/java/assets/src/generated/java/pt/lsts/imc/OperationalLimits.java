package pt.lsts.imc;

public class OperationalLimits extends IMCMessage {
    public static final int ID_STATIC = 504;

public static final short OPL_MAX_DEPTH = 0x01;
public static final short OPL_MIN_ALT = 0x02;
public static final short OPL_MAX_ALT = 0x04;
public static final short OPL_MIN_SPEED = 0x08;
public static final short OPL_MAX_SPEED = 0x10;
public static final short OPL_MAX_VRATE = 0x20;
public static final short OPL_AREA = 0x40;

    public OperationalLimits() {
        super(ID_STATIC);
    }

    public OperationalLimits(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public OperationalLimits(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OperationalLimits create(Object... values) {
        OperationalLimits m = new OperationalLimits();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static OperationalLimits clone(IMCMessage msg) throws Exception {
        OperationalLimits m = new OperationalLimits();
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

    public short getMask() {
        return getShort("mask");
    }

    public OperationalLimits setMask(short mask) {
        values.put("mask", mask);
        return this;
    }

    public double getMaxDepth() {
        return getDouble("max_depth");
    }

    public OperationalLimits setMaxDepth(double max_depth) {
        values.put("max_depth", max_depth);
        return this;
    }

    public double getMinAltitude() {
        return getDouble("min_altitude");
    }

    public OperationalLimits setMinAltitude(double min_altitude) {
        values.put("min_altitude", min_altitude);
        return this;
    }

    public double getMaxAltitude() {
        return getDouble("max_altitude");
    }

    public OperationalLimits setMaxAltitude(double max_altitude) {
        values.put("max_altitude", max_altitude);
        return this;
    }

    public double getMinSpeed() {
        return getDouble("min_speed");
    }

    public OperationalLimits setMinSpeed(double min_speed) {
        values.put("min_speed", min_speed);
        return this;
    }

    public double getMaxSpeed() {
        return getDouble("max_speed");
    }

    public OperationalLimits setMaxSpeed(double max_speed) {
        values.put("max_speed", max_speed);
        return this;
    }

    public double getMaxVrate() {
        return getDouble("max_vrate");
    }

    public OperationalLimits setMaxVrate(double max_vrate) {
        values.put("max_vrate", max_vrate);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public OperationalLimits setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public OperationalLimits setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getOrientation() {
        return getDouble("orientation");
    }

    public OperationalLimits setOrientation(double orientation) {
        values.put("orientation", orientation);
        return this;
    }

    public double getWidth() {
        return getDouble("width");
    }

    public OperationalLimits setWidth(double width) {
        values.put("width", width);
        return this;
    }

    public double getLength() {
        return getDouble("length");
    }

    public OperationalLimits setLength(double length) {
        values.put("length", length);
        return this;
    }

}
