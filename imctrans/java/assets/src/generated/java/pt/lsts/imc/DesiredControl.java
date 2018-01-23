package pt.lsts.imc;

public class DesiredControl extends IMCMessage {
    public static final int ID_STATIC = 407;

public static final short FL_X = 0x01;
public static final short FL_Y = 0x02;
public static final short FL_Z = 0x04;
public static final short FL_K = 0x08;
public static final short FL_M = 0x10;
public static final short FL_N = 0x20;

    public DesiredControl() {
        super(ID_STATIC);
    }

    public DesiredControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredControl create(Object... values) {
        DesiredControl m = new DesiredControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredControl clone(IMCMessage msg) throws Exception {
        DesiredControl m = new DesiredControl();
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

    public DesiredControl setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public DesiredControl setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public DesiredControl setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getK() {
        return getDouble("k");
    }

    public DesiredControl setK(double k) {
        values.put("k", k);
        return this;
    }

    public double getM() {
        return getDouble("m");
    }

    public DesiredControl setM(double m) {
        values.put("m", m);
        return this;
    }

    public double getN() {
        return getDouble("n");
    }

    public DesiredControl setN(double n) {
        values.put("n", n);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public DesiredControl setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

}
