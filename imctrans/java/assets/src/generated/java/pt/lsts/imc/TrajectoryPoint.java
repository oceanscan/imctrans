package pt.lsts.imc;

public class TrajectoryPoint extends IMCMessage {
    public static final int ID_STATIC = 464;


    public TrajectoryPoint() {
        super(ID_STATIC);
    }

    public TrajectoryPoint(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TrajectoryPoint(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrajectoryPoint create(Object... values) {
        TrajectoryPoint m = new TrajectoryPoint();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TrajectoryPoint clone(IMCMessage msg) throws Exception {
        TrajectoryPoint m = new TrajectoryPoint();
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

    public TrajectoryPoint setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public TrajectoryPoint setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public TrajectoryPoint setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getT() {
        return getDouble("t");
    }

    public TrajectoryPoint setT(double t) {
        values.put("t", t);
        return this;
    }

}
