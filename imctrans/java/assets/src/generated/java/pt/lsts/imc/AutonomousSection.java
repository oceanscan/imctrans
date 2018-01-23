package pt.lsts.imc;

public class AutonomousSection extends Maneuver {
    public static final int ID_STATIC = 493;

public static final short ENFORCE_DEPTH = 0x01;
public static final short ENFORCE_ALTITUDE = 0x02;
public static final short ENFORCE_TIMEOUT = 0x04;
public static final short ENFORCE_AREA2D = 0x08;

    public enum SPEED_UNITS {
        METERS_PS(0),
        RPM(1),
        PERCENTAGE(2);

        protected long value;

        public long value() {
            return value;
        }

        SPEED_UNITS(long value) {
            this.value = value;
        }
    }

    public AutonomousSection() {
        super(ID_STATIC);
    }

    public AutonomousSection(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AutonomousSection(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AutonomousSection create(Object... values) {
        AutonomousSection m = new AutonomousSection();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AutonomousSection clone(IMCMessage msg) throws Exception {
        AutonomousSection m = new AutonomousSection();
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

    public AutonomousSection setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public AutonomousSection setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public AutonomousSection setSpeed(double speed) {
        values.put("speed", speed);
        return this;
    }

    public SPEED_UNITS getSpeedUnits() {
        try {
            SPEED_UNITS o = SPEED_UNITS.valueOf(getMessageType().getFieldPossibleValues("speed_units").get(getLong("speed_units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSpeedUnitsStr() {
        return getString("speed_units");
    }

    public short getSpeedUnitsVal() {
        return getShort("speed_units");
    }

    public AutonomousSection setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public AutonomousSection setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public AutonomousSection setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public short getLimits() {
        return getShort("limits");
    }

    public AutonomousSection setLimits(short limits) {
        values.put("limits", limits);
        return this;
    }

    public double getMaxDepth() {
        return getDouble("max_depth");
    }

    public AutonomousSection setMaxDepth(double max_depth) {
        values.put("max_depth", max_depth);
        return this;
    }

    public double getMinAlt() {
        return getDouble("min_alt");
    }

    public AutonomousSection setMinAlt(double min_alt) {
        values.put("min_alt", min_alt);
        return this;
    }

    public double getTimeLimit() {
        return getDouble("time_limit");
    }

    public AutonomousSection setTimeLimit(double time_limit) {
        values.put("time_limit", time_limit);
        return this;
    }

    public java.util.Vector<PolygonVertex> getAreaLimits() {
        return getMessageListOrNull("area_limits", PolygonVertex.class);
    }

    public AutonomousSection setAreaLimits(java.util.Collection<PolygonVertex> area_limits) {
        values.put("area_limits", area_limits);
        return this;
    }

    public String getController() {
        return getString("controller");
    }

    public AutonomousSection setController(String controller) {
        values.put("controller", controller);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public AutonomousSection setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public AutonomousSection setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
