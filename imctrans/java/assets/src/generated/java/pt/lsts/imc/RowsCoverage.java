package pt.lsts.imc;

public class RowsCoverage extends Maneuver {
    public static final int ID_STATIC = 488;

public static final short FLG_SQUARE_CURVE = 0x01;
public static final short FLG_CURVE_RIGHT = 0x02;

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

    public RowsCoverage() {
        super(ID_STATIC);
    }

    public RowsCoverage(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RowsCoverage(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RowsCoverage create(Object... values) {
        RowsCoverage m = new RowsCoverage();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RowsCoverage clone(IMCMessage msg) throws Exception {
        RowsCoverage m = new RowsCoverage();
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

    public RowsCoverage setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public RowsCoverage setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public RowsCoverage setZ(double z) {
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

    public RowsCoverage setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public RowsCoverage setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public RowsCoverage setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public RowsCoverage setSpeed(double speed) {
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

    public RowsCoverage setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public RowsCoverage setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public RowsCoverage setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public double getBearing() {
        return getDouble("bearing");
    }

    public RowsCoverage setBearing(double bearing) {
        values.put("bearing", bearing);
        return this;
    }

    public double getCrossAngle() {
        return getDouble("cross_angle");
    }

    public RowsCoverage setCrossAngle(double cross_angle) {
        values.put("cross_angle", cross_angle);
        return this;
    }

    public double getWidth() {
        return getDouble("width");
    }

    public RowsCoverage setWidth(double width) {
        values.put("width", width);
        return this;
    }

    public double getLength() {
        return getDouble("length");
    }

    public RowsCoverage setLength(double length) {
        values.put("length", length);
        return this;
    }

    public short getCoff() {
        return getShort("coff");
    }

    public RowsCoverage setCoff(short coff) {
        values.put("coff", coff);
        return this;
    }

    public double getAngaperture() {
        return getDouble("angAperture");
    }

    public RowsCoverage setAngaperture(double angAperture) {
        values.put("angAperture", angAperture);
        return this;
    }

    public int getRange() {
        return getInteger("range");
    }

    public RowsCoverage setRange(int range) {
        values.put("range", range);
        return this;
    }

    public short getOverlap() {
        return getShort("overlap");
    }

    public RowsCoverage setOverlap(short overlap) {
        values.put("overlap", overlap);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public RowsCoverage setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public RowsCoverage setCustom(java.util.LinkedHashMap<String, ?> custom) {
        values.put("custom", encodeTupleList(custom));
        return this;
    }

    public RowsCoverage setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
