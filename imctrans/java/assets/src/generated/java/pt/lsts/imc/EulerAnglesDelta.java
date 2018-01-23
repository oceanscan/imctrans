package pt.lsts.imc;

public class EulerAnglesDelta extends IMCMessage {
    public static final int ID_STATIC = 255;


    public EulerAnglesDelta() {
        super(ID_STATIC);
    }

    public EulerAnglesDelta(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EulerAnglesDelta(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EulerAnglesDelta create(Object... values) {
        EulerAnglesDelta m = new EulerAnglesDelta();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EulerAnglesDelta clone(IMCMessage msg) throws Exception {
        EulerAnglesDelta m = new EulerAnglesDelta();
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

    public EulerAnglesDelta setTime(double time) {
        values.put("time", time);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public EulerAnglesDelta setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public EulerAnglesDelta setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public EulerAnglesDelta setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getTimestep() {
        return getDouble("timestep");
    }

    public EulerAnglesDelta setTimestep(double timestep) {
        values.put("timestep", timestep);
        return this;
    }

}
