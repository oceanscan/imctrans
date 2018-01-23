package pt.lsts.imc;

public class Acceleration extends IMCMessage {
    public static final int ID_STATIC = 257;


    public Acceleration() {
        super(ID_STATIC);
    }

    public Acceleration(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Acceleration(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Acceleration create(Object... values) {
        Acceleration m = new Acceleration();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Acceleration clone(IMCMessage msg) throws Exception {
        Acceleration m = new Acceleration();
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

    public Acceleration setTime(double time) {
        values.put("time", time);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public Acceleration setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public Acceleration setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public Acceleration setZ(double z) {
        values.put("z", z);
        return this;
    }

}
