package pt.lsts.imc;

public class MagneticField extends IMCMessage {
    public static final int ID_STATIC = 258;


    public MagneticField() {
        super(ID_STATIC);
    }

    public MagneticField(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public MagneticField(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MagneticField create(Object... values) {
        MagneticField m = new MagneticField();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static MagneticField clone(IMCMessage msg) throws Exception {
        MagneticField m = new MagneticField();
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

    public MagneticField setTime(double time) {
        values.put("time", time);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public MagneticField setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public MagneticField setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public MagneticField setZ(double z) {
        values.put("z", z);
        return this;
    }

}
