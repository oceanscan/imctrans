package pt.lsts.imc;

public class ControlParcel extends IMCMessage {
    public static final int ID_STATIC = 412;


    public ControlParcel() {
        super(ID_STATIC);
    }

    public ControlParcel(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ControlParcel(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ControlParcel create(Object... values) {
        ControlParcel m = new ControlParcel();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ControlParcel clone(IMCMessage msg) throws Exception {
        ControlParcel m = new ControlParcel();
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

    public double getP() {
        return getDouble("p");
    }

    public ControlParcel setP(double p) {
        values.put("p", p);
        return this;
    }

    public double getI() {
        return getDouble("i");
    }

    public ControlParcel setI(double i) {
        values.put("i", i);
        return this;
    }

    public double getD() {
        return getDouble("d");
    }

    public ControlParcel setD(double d) {
        values.put("d", d);
        return this;
    }

    public double getA() {
        return getDouble("a");
    }

    public ControlParcel setA(double a) {
        values.put("a", a);
        return this;
    }

}
