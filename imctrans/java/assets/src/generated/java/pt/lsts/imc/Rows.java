package pt.lsts.imc;

public class Rows extends Maneuver {
    public static final int ID_STATIC = 456;

public static final short FLG_SQUARE_CURVE = 0x0001;
public static final short FLG_CURVE_RIGHT = 0x0002;

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

    public Rows() {
        super(ID_STATIC);
    }

    public Rows(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Rows(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Rows create(Object... values) {
        Rows m = new Rows();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Rows clone(IMCMessage msg) throws Exception {
        Rows m = new Rows();
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

    public Rows setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Rows setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Rows setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public Rows setZ(double z) {
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

    public Rows setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Rows setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Rows setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public Rows setSpeed(double speed) {
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

    public Rows setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Rows setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Rows setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public double getBearing() {
        return getDouble("bearing");
    }

    public Rows setBearing(double bearing) {
        values.put("bearing", bearing);
        return this;
    }

    public double getCrossAngle() {
        return getDouble("cross_angle");
    }

    public Rows setCrossAngle(double cross_angle) {
        values.put("cross_angle", cross_angle);
        return this;
    }

    public double getWidth() {
        return getDouble("width");
    }

    public Rows setWidth(double width) {
        values.put("width", width);
        return this;
    }

    public double getLength() {
        return getDouble("length");
    }

    public Rows setLength(double length) {
        values.put("length", length);
        return this;
    }

    public double getHstep() {
        return getDouble("hstep");
    }

    public Rows setHstep(double hstep) {
        values.put("hstep", hstep);
        return this;
    }

    public short getCoff() {
        return getShort("coff");
    }

    public Rows setCoff(short coff) {
        values.put("coff", coff);
        return this;
    }

    public short getAlternation() {
        return getShort("alternation");
    }

    public Rows setAlternation(short alternation) {
        values.put("alternation", alternation);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public Rows setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Rows setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public Rows setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
