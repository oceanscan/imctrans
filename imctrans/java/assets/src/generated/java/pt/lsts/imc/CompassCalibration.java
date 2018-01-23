package pt.lsts.imc;

public class CompassCalibration extends Maneuver {
    public static final int ID_STATIC = 475;


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

    public enum DIRECTION {
        VDEP(0),
        CLOCKW(1),
        CCLOCKW(2),
        IWINDCURR(3);

        protected long value;

        public long value() {
            return value;
        }

        DIRECTION(long value) {
            this.value = value;
        }
    }

    public CompassCalibration() {
        super(ID_STATIC);
    }

    public CompassCalibration(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CompassCalibration(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CompassCalibration create(Object... values) {
        CompassCalibration m = new CompassCalibration();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CompassCalibration clone(IMCMessage msg) throws Exception {
        CompassCalibration m = new CompassCalibration();
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

    public CompassCalibration setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public CompassCalibration setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public CompassCalibration setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public CompassCalibration setZ(double z) {
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

    public CompassCalibration setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public CompassCalibration setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public CompassCalibration setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getPitch() {
        return getDouble("pitch");
    }

    public CompassCalibration setPitch(double pitch) {
        values.put("pitch", pitch);
        return this;
    }

    public double getAmplitude() {
        return getDouble("amplitude");
    }

    public CompassCalibration setAmplitude(double amplitude) {
        values.put("amplitude", amplitude);
        return this;
    }

    public int getDuration() {
        return getInteger("duration");
    }

    public CompassCalibration setDuration(int duration) {
        values.put("duration", duration);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public CompassCalibration setSpeed(double speed) {
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

    public CompassCalibration setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public CompassCalibration setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public CompassCalibration setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public double getRadius() {
        return getDouble("radius");
    }

    public CompassCalibration setRadius(double radius) {
        values.put("radius", radius);
        return this;
    }

    public DIRECTION getDirection() {
        try {
            DIRECTION o = DIRECTION.valueOf(getMessageType().getFieldPossibleValues("direction").get(getLong("direction")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getDirectionStr() {
        return getString("direction");
    }

    public short getDirectionVal() {
        return getShort("direction");
    }

    public CompassCalibration setDirectionStr(String direction) {
        setValue("direction", direction);
        return this;
    }

    public CompassCalibration setDirectionVal(DIRECTION direction) {
        setValue("direction", direction);
        return this;
    }

    public CompassCalibration setDirection(DIRECTION direction) {
        values.put("direction", direction.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public CompassCalibration setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public CompassCalibration setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
