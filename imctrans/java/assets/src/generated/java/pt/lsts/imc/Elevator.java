package pt.lsts.imc;

public class Elevator extends Maneuver {
    public static final int ID_STATIC = 462;

public static final short FLG_CURR_POS = 0x01;

    public enum START_Z_UNITS {
        NONE(0),
        DEPTH(1),
        ALTITUDE(2),
        HEIGHT(3);

        protected long value;

        public long value() {
            return value;
        }

        START_Z_UNITS(long value) {
            this.value = value;
        }
    }

    public enum END_Z_UNITS {
        NONE(0),
        DEPTH(1),
        ALTITUDE(2),
        HEIGHT(3);

        protected long value;

        public long value() {
            return value;
        }

        END_Z_UNITS(long value) {
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

    public Elevator() {
        super(ID_STATIC);
    }

    public Elevator(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Elevator(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Elevator create(Object... values) {
        Elevator m = new Elevator();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Elevator clone(IMCMessage msg) throws Exception {
        Elevator m = new Elevator();
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

    public Elevator setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public Elevator setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Elevator setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Elevator setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getStartZ() {
        return getDouble("start_z");
    }

    public Elevator setStartZ(double start_z) {
        values.put("start_z", start_z);
        return this;
    }

    public START_Z_UNITS getStartZUnits() {
        try {
            START_Z_UNITS o = START_Z_UNITS.valueOf(getMessageType().getFieldPossibleValues("start_z_units").get(getLong("start_z_units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getStartZUnitsStr() {
        return getString("start_z_units");
    }

    public short getStartZUnitsVal() {
        return getShort("start_z_units");
    }

    public Elevator setStartZUnitsStr(String start_z_units) {
        setValue("start_z_units", start_z_units);
        return this;
    }

    public Elevator setStartZUnitsVal(START_Z_UNITS start_z_units) {
        setValue("start_z_units", start_z_units);
        return this;
    }

    public Elevator setStartZUnits(START_Z_UNITS start_z_units) {
        values.put("start_z_units", start_z_units.value());
        return this;
    }

    public double getEndZ() {
        return getDouble("end_z");
    }

    public Elevator setEndZ(double end_z) {
        values.put("end_z", end_z);
        return this;
    }

    public END_Z_UNITS getEndZUnits() {
        try {
            END_Z_UNITS o = END_Z_UNITS.valueOf(getMessageType().getFieldPossibleValues("end_z_units").get(getLong("end_z_units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getEndZUnitsStr() {
        return getString("end_z_units");
    }

    public short getEndZUnitsVal() {
        return getShort("end_z_units");
    }

    public Elevator setEndZUnitsStr(String end_z_units) {
        setValue("end_z_units", end_z_units);
        return this;
    }

    public Elevator setEndZUnitsVal(END_Z_UNITS end_z_units) {
        setValue("end_z_units", end_z_units);
        return this;
    }

    public Elevator setEndZUnits(END_Z_UNITS end_z_units) {
        values.put("end_z_units", end_z_units.value());
        return this;
    }

    public double getRadius() {
        return getDouble("radius");
    }

    public Elevator setRadius(double radius) {
        values.put("radius", radius);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public Elevator setSpeed(double speed) {
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

    public Elevator setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Elevator setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Elevator setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Elevator setCustom(java.util.LinkedHashMap<String, ?> custom) {
        values.put("custom", encodeTupleList(custom));
        return this;
    }

    public Elevator setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
