package pt.lsts.imc;

public class PathControlState extends IMCMessage {
    public static final int ID_STATIC = 410;

public static final short FL_NEAR = 0x01;
public static final short FL_LOITERING = 0x02;
public static final short FL_NO_Z = 0x04;
public static final short FL_3DTRACK = 0x08;
public static final short FL_CCLOCKW = 0x10;

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

    public PathControlState() {
        super(ID_STATIC);
    }

    public PathControlState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PathControlState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PathControlState create(Object... values) {
        PathControlState m = new PathControlState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PathControlState clone(IMCMessage msg) throws Exception {
        PathControlState m = new PathControlState();
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

    public long getPathRef() {
        return getLong("path_ref");
    }

    public PathControlState setPathRef(long path_ref) {
        values.put("path_ref", path_ref);
        return this;
    }

    public double getStartLat() {
        return getDouble("start_lat");
    }

    public PathControlState setStartLat(double start_lat) {
        values.put("start_lat", start_lat);
        return this;
    }

    public double getStartLon() {
        return getDouble("start_lon");
    }

    public PathControlState setStartLon(double start_lon) {
        values.put("start_lon", start_lon);
        return this;
    }

    public double getStartZ() {
        return getDouble("start_z");
    }

    public PathControlState setStartZ(double start_z) {
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

    public PathControlState setStartZUnitsStr(String start_z_units) {
        setValue("start_z_units", start_z_units);
        return this;
    }

    public PathControlState setStartZUnitsVal(START_Z_UNITS start_z_units) {
        setValue("start_z_units", start_z_units);
        return this;
    }

    public PathControlState setStartZUnits(START_Z_UNITS start_z_units) {
        values.put("start_z_units", start_z_units.value());
        return this;
    }

    public double getEndLat() {
        return getDouble("end_lat");
    }

    public PathControlState setEndLat(double end_lat) {
        values.put("end_lat", end_lat);
        return this;
    }

    public double getEndLon() {
        return getDouble("end_lon");
    }

    public PathControlState setEndLon(double end_lon) {
        values.put("end_lon", end_lon);
        return this;
    }

    public double getEndZ() {
        return getDouble("end_z");
    }

    public PathControlState setEndZ(double end_z) {
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

    public PathControlState setEndZUnitsStr(String end_z_units) {
        setValue("end_z_units", end_z_units);
        return this;
    }

    public PathControlState setEndZUnitsVal(END_Z_UNITS end_z_units) {
        setValue("end_z_units", end_z_units);
        return this;
    }

    public PathControlState setEndZUnits(END_Z_UNITS end_z_units) {
        values.put("end_z_units", end_z_units.value());
        return this;
    }

    public double getLradius() {
        return getDouble("lradius");
    }

    public PathControlState setLradius(double lradius) {
        values.put("lradius", lradius);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public PathControlState setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public PathControlState setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public PathControlState setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public PathControlState setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getVx() {
        return getDouble("vx");
    }

    public PathControlState setVx(double vx) {
        values.put("vx", vx);
        return this;
    }

    public double getVy() {
        return getDouble("vy");
    }

    public PathControlState setVy(double vy) {
        values.put("vy", vy);
        return this;
    }

    public double getVz() {
        return getDouble("vz");
    }

    public PathControlState setVz(double vz) {
        values.put("vz", vz);
        return this;
    }

    public double getCourseError() {
        return getDouble("course_error");
    }

    public PathControlState setCourseError(double course_error) {
        values.put("course_error", course_error);
        return this;
    }

    public int getEta() {
        return getInteger("eta");
    }

    public PathControlState setEta(int eta) {
        values.put("eta", eta);
        return this;
    }

}
