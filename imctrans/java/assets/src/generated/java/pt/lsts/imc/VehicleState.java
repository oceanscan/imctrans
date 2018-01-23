package pt.lsts.imc;

public class VehicleState extends IMCMessage {
    public static final int ID_STATIC = 500;

public static final long CL_NONE = 0x00000000;
public static final long CL_PATH = 0x00000001;
public static final long CL_TELEOPERATION = 0x00000002;
public static final long CL_ALTITUDE = 0x00000004;
public static final long CL_DEPTH = 0x00000008;
public static final long CL_ROLL = 0x00000010;
public static final long CL_PITCH = 0x00000020;
public static final long CL_YAW = 0x00000040;
public static final long CL_SPEED = 0x00000080;
public static final long CL_YAW_RATE = 0x00000100;
public static final long CL_VERTICAL_RATE = 0x00000200;
public static final long CL_TORQUE = 0x00000400;
public static final long CL_FORCE = 0x00000800;
public static final long CL_VELOCITY = 0x00001000;
public static final long CL_THROTTLE = 0x00002000;
public static final long CL_EXTERNAL = 0x40000000;
public static final long CL_NO_OVERRIDE = 0x80000000;
public static final long CL_ALL = 0xFFFFFFFF;
public static final short VFLG_MANEUVER_DONE = 0x01;

    public enum OP_MODE {
        SERVICE(0),
        CALIBRATION(1),
        ERROR(2),
        MANEUVER(3),
        EXTERNAL(4),
        BOOT(5);

        protected long value;

        public long value() {
            return value;
        }

        OP_MODE(long value) {
            this.value = value;
        }
    }

    public VehicleState() {
        super(ID_STATIC);
    }

    public VehicleState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VehicleState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VehicleState create(Object... values) {
        VehicleState m = new VehicleState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VehicleState clone(IMCMessage msg) throws Exception {
        VehicleState m = new VehicleState();
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

    public OP_MODE getOpMode() {
        try {
            OP_MODE o = OP_MODE.valueOf(getMessageType().getFieldPossibleValues("op_mode").get(getLong("op_mode")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getOpModeStr() {
        return getString("op_mode");
    }

    public short getOpModeVal() {
        return getShort("op_mode");
    }

    public VehicleState setOpModeStr(String op_mode) {
        setValue("op_mode", op_mode);
        return this;
    }

    public VehicleState setOpModeVal(OP_MODE op_mode) {
        setValue("op_mode", op_mode);
        return this;
    }

    public VehicleState setOpMode(OP_MODE op_mode) {
        values.put("op_mode", op_mode.value());
        return this;
    }

    public short getErrorCount() {
        return getShort("error_count");
    }

    public VehicleState setErrorCount(short error_count) {
        values.put("error_count", error_count);
        return this;
    }

    public String getErrorEnts() {
        return getString("error_ents");
    }

    public VehicleState setErrorEnts(String error_ents) {
        values.put("error_ents", error_ents);
        return this;
    }

    public int getManeuverType() {
        return getInteger("maneuver_type");
    }

    public VehicleState setManeuverType(int maneuver_type) {
        values.put("maneuver_type", maneuver_type);
        return this;
    }

    public double getManeuverStime() {
        return getDouble("maneuver_stime");
    }

    public VehicleState setManeuverStime(double maneuver_stime) {
        values.put("maneuver_stime", maneuver_stime);
        return this;
    }

    public int getManeuverEta() {
        return getInteger("maneuver_eta");
    }

    public VehicleState setManeuverEta(int maneuver_eta) {
        values.put("maneuver_eta", maneuver_eta);
        return this;
    }

    public long getControlLoops() {
        return getLong("control_loops");
    }

    public VehicleState setControlLoops(long control_loops) {
        values.put("control_loops", control_loops);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public VehicleState setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public String getLastError() {
        return getString("last_error");
    }

    public VehicleState setLastError(String last_error) {
        values.put("last_error", last_error);
        return this;
    }

    public double getLastErrorTime() {
        return getDouble("last_error_time");
    }

    public VehicleState setLastErrorTime(double last_error_time) {
        values.put("last_error_time", last_error_time);
        return this;
    }

}
