package pt.lsts.imc;

public class VelocityDelta extends IMCMessage {
    public static final int ID_STATIC = 261;


    public VelocityDelta() {
        super(ID_STATIC);
    }

    public VelocityDelta(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VelocityDelta(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VelocityDelta create(Object... values) {
        VelocityDelta m = new VelocityDelta();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VelocityDelta clone(IMCMessage msg) throws Exception {
        VelocityDelta m = new VelocityDelta();
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

    public VelocityDelta setTime(double time) {
        values.put("time", time);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public VelocityDelta setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public VelocityDelta setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public VelocityDelta setZ(double z) {
        values.put("z", z);
        return this;
    }

}
