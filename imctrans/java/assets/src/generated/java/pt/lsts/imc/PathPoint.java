package pt.lsts.imc;

public class PathPoint extends IMCMessage {
    public static final int ID_STATIC = 458;


    public PathPoint() {
        super(ID_STATIC);
    }

    public PathPoint(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PathPoint(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PathPoint create(Object... values) {
        PathPoint m = new PathPoint();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PathPoint clone(IMCMessage msg) throws Exception {
        PathPoint m = new PathPoint();
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

    public PathPoint setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public PathPoint setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public PathPoint setZ(double z) {
        values.put("z", z);
        return this;
    }

}
