package pt.lsts.imc;

public class AngularVelocity extends IMCMessage {
    public static final int ID_STATIC = 256;


    public AngularVelocity() {
        super(ID_STATIC);
    }

    public AngularVelocity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AngularVelocity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AngularVelocity create(Object... values) {
        AngularVelocity m = new AngularVelocity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AngularVelocity clone(IMCMessage msg) throws Exception {
        AngularVelocity m = new AngularVelocity();
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

    public double getTime() {
        return getDouble("time");
    }

    public AngularVelocity setTime(double time) {
        values.put("time", time);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public AngularVelocity setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public AngularVelocity setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public AngularVelocity setZ(double z) {
        values.put("z", z);
        return this;
    }

}
