package pt.lsts.imc;

public class DesiredVelocity extends IMCMessage {
    public static final int ID_STATIC = 409;

public static final short FL_SURGE = 0x01;
public static final short FL_SWAY = 0x02;
public static final short FL_HEAVE = 0x04;
public static final short FL_ROLL = 0x08;
public static final short FL_PITCH = 0x10;
public static final short FL_YAW = 0x20;

    public DesiredVelocity() {
        super(ID_STATIC);
    }

    public DesiredVelocity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredVelocity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredVelocity create(Object... values) {
        DesiredVelocity m = new DesiredVelocity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredVelocity clone(IMCMessage msg) throws Exception {
        DesiredVelocity m = new DesiredVelocity();
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

    public double getU() {
        return getDouble("u");
    }

    public DesiredVelocity setU(double u) {
        values.put("u", u);
        return this;
    }

    public double getV() {
        return getDouble("v");
    }

    public DesiredVelocity setV(double v) {
        values.put("v", v);
        return this;
    }

    public double getW() {
        return getDouble("w");
    }

    public DesiredVelocity setW(double w) {
        values.put("w", w);
        return this;
    }

    public double getP() {
        return getDouble("p");
    }

    public DesiredVelocity setP(double p) {
        values.put("p", p);
        return this;
    }

    public double getQ() {
        return getDouble("q");
    }

    public DesiredVelocity setQ(double q) {
        values.put("q", q);
        return this;
    }

    public double getR() {
        return getDouble("r");
    }

    public DesiredVelocity setR(double r) {
        values.put("r", r);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public DesiredVelocity setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

}
