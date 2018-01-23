package pt.lsts.imc;

public class DesiredPath extends ControlCommand {
    public static final int ID_STATIC = 406;

public static final short FL_START = 0x01;
public static final short FL_DIRECT = 0x02;
public static final short FL_NO_Z = 0x04;
public static final short FL_3DTRACK = 0x08;
public static final short FL_CCLOCKW = 0x10;
public static final short FL_LOITER_CURR = 0x20;
public static final short FL_TAKEOFF = 0x40;
public static final short FL_LAND = 0x80;

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

    public DesiredPath() {
        super(ID_STATIC);
    }

    public DesiredPath(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredPath(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredPath create(Object... values) {
        DesiredPath m = new DesiredPath();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredPath clone(IMCMessage msg) throws Exception {
        DesiredPath m = new DesiredPath();
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

    public DesiredPath setPathRef(long path_ref) {
        values.put("path_ref", path_ref);
        return this;
    }

    public double getStartLat() {
        return getDouble("start_lat");
    }

    public DesiredPath setStartLat(double start_lat) {
        values.put("start_lat", start_lat);
        return this;
    }

    public double getStartLon() {
        return getDouble("start_lon");
    }

    public DesiredPath setStartLon(double start_lon) {
        values.put("start_lon", start_lon);
        return this;
    }

    public double getStartZ() {
        return getDouble("start_z");
    }

    public DesiredPath setStartZ(double start_z) {
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

    public DesiredPath setStartZUnitsStr(String start_z_units) {
        setValue("start_z_units", start_z_units);
        return this;
    }

    public DesiredPath setStartZUnitsVal(START_Z_UNITS start_z_units) {
        setValue("start_z_units", start_z_units);
        return this;
    }

    public DesiredPath setStartZUnits(START_Z_UNITS start_z_units) {
        values.put("start_z_units", start_z_units.value());
        return this;
    }

    public double getEndLat() {
        return getDouble("end_lat");
    }

    public DesiredPath setEndLat(double end_lat) {
        values.put("end_lat", end_lat);
        return this;
    }

    public double getEndLon() {
        return getDouble("end_lon");
    }

    public DesiredPath setEndLon(double end_lon) {
        values.put("end_lon", end_lon);
        return this;
    }

    public double getEndZ() {
        return getDouble("end_z");
    }

    public DesiredPath setEndZ(double end_z) {
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

    public DesiredPath setEndZUnitsStr(String end_z_units) {
        setValue("end_z_units", end_z_units);
        return this;
    }

    public DesiredPath setEndZUnitsVal(END_Z_UNITS end_z_units) {
        setValue("end_z_units", end_z_units);
        return this;
    }

    public DesiredPath setEndZUnits(END_Z_UNITS end_z_units) {
        values.put("end_z_units", end_z_units.value());
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public DesiredPath setSpeed(double speed) {
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

    public DesiredPath setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public DesiredPath setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public DesiredPath setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public double getLradius() {
        return getDouble("lradius");
    }

    public DesiredPath setLradius(double lradius) {
        values.put("lradius", lradius);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public DesiredPath setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

}
