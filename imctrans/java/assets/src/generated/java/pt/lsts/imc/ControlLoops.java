package pt.lsts.imc;

public class ControlLoops extends IMCMessage {
    public static final int ID_STATIC = 507;

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

    public enum ENABLE {
        DISABLE(0),
        ENABLE(1);

        protected long value;

        public long value() {
            return value;
        }

        ENABLE(long value) {
            this.value = value;
        }
    }

    public ControlLoops() {
        super(ID_STATIC);
    }

    public ControlLoops(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ControlLoops(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ControlLoops create(Object... values) {
        ControlLoops m = new ControlLoops();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ControlLoops clone(IMCMessage msg) throws Exception {
        ControlLoops m = new ControlLoops();
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

    public ENABLE getEnable() {
        try {
            ENABLE o = ENABLE.valueOf(getMessageType().getFieldPossibleValues("enable").get(getLong("enable")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getEnableStr() {
        return getString("enable");
    }

    public short getEnableVal() {
        return getShort("enable");
    }

    public ControlLoops setEnableStr(String enable) {
        setValue("enable", enable);
        return this;
    }

    public ControlLoops setEnableVal(ENABLE enable) {
        setValue("enable", enable);
        return this;
    }

    public ControlLoops setEnable(ENABLE enable) {
        values.put("enable", enable.value());
        return this;
    }

    public long getMask() {
        return getLong("mask");
    }

    public ControlLoops setMask(long mask) {
        values.put("mask", mask);
        return this;
    }

    public long getScopeRef() {
        return getLong("scope_ref");
    }

    public ControlLoops setScopeRef(long scope_ref) {
        values.put("scope_ref", scope_ref);
        return this;
    }

}
