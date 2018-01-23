package pt.lsts.imc;

public class DesiredLinearState extends IMCMessage {
    public static final int ID_STATIC = 414;

public static final int FL_X = 0x0001;
public static final int FL_Y = 0x0002;
public static final int FL_Z = 0x0004;
public static final int FL_VX = 0x0008;
public static final int FL_VY = 0x0010;
public static final int FL_VZ = 0x0020;
public static final int FL_AX = 0x0040;
public static final int FL_AY = 0x0080;
public static final int FL_AZ = 0x0100;

    public DesiredLinearState() {
        super(ID_STATIC);
    }

    public DesiredLinearState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredLinearState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredLinearState create(Object... values) {
        DesiredLinearState m = new DesiredLinearState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredLinearState clone(IMCMessage msg) throws Exception {
        DesiredLinearState m = new DesiredLinearState();
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

    public double getX() {
        return getDouble("x");
    }

    public DesiredLinearState setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public DesiredLinearState setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public DesiredLinearState setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getVx() {
        return getDouble("vx");
    }

    public DesiredLinearState setVx(double vx) {
        values.put("vx", vx);
        return this;
    }

    public double getVy() {
        return getDouble("vy");
    }

    public DesiredLinearState setVy(double vy) {
        values.put("vy", vy);
        return this;
    }

    public double getVz() {
        return getDouble("vz");
    }

    public DesiredLinearState setVz(double vz) {
        values.put("vz", vz);
        return this;
    }

    public double getAx() {
        return getDouble("ax");
    }

    public DesiredLinearState setAx(double ax) {
        values.put("ax", ax);
        return this;
    }

    public double getAy() {
        return getDouble("ay");
    }

    public DesiredLinearState setAy(double ay) {
        values.put("ay", ay);
        return this;
    }

    public double getAz() {
        return getDouble("az");
    }

    public DesiredLinearState setAz(double az) {
        values.put("az", az);
        return this;
    }

    public int getFlags() {
        return getInteger("flags");
    }

    public DesiredLinearState setFlags(int flags) {
        values.put("flags", flags);
        return this;
    }

}
