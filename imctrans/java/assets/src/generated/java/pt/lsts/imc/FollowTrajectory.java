package pt.lsts.imc;

public class FollowTrajectory extends Maneuver {
    public static final int ID_STATIC = 463;


    public enum Z_UNITS {
        NONE(0),
        DEPTH(1),
        ALTITUDE(2),
        HEIGHT(3);

        protected long value;

        public long value() {
            return value;
        }

        Z_UNITS(long value) {
            this.value = value;
        }
    }

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

    public FollowTrajectory() {
        super(ID_STATIC);
    }

    public FollowTrajectory(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FollowTrajectory(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FollowTrajectory create(Object... values) {
        FollowTrajectory m = new FollowTrajectory();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FollowTrajectory clone(IMCMessage msg) throws Exception {
        FollowTrajectory m = new FollowTrajectory();
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

    public int getTimeout() {
        return getInteger("timeout");
    }

    public FollowTrajectory setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public FollowTrajectory setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public FollowTrajectory setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public FollowTrajectory setZ(double z) {
        values.put("z", z);
        return this;
    }

    public Z_UNITS getZUnits() {
        try {
            Z_UNITS o = Z_UNITS.valueOf(getMessageType().getFieldPossibleValues("z_units").get(getLong("z_units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getZUnitsStr() {
        return getString("z_units");
    }

    public short getZUnitsVal() {
        return getShort("z_units");
    }

    public FollowTrajectory setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public FollowTrajectory setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public FollowTrajectory setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public FollowTrajectory setSpeed(double speed) {
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

    public FollowTrajectory setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public FollowTrajectory setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public FollowTrajectory setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public java.util.Vector<TrajectoryPoint> getPoints() {
        return getMessageListOrNull("points", TrajectoryPoint.class);
    }

    public FollowTrajectory setPoints(java.util.Collection<TrajectoryPoint> points) {
        values.put("points", points);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public FollowTrajectory setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public FollowTrajectory setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
