package pt.lsts.imc;

public class Pressure extends IMCMessage {
    public static final int ID_STATIC = 264;


    public Pressure() {
        super(ID_STATIC);
    }

    public Pressure(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Pressure(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Pressure create(Object... values) {
        Pressure m = new Pressure();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Pressure clone(IMCMessage msg) throws Exception {
        Pressure m = new Pressure();
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

    public double getValue() {
        return getDouble("value");
    }

    public Pressure setValue(double value) {
        values.put("value", value);
        return this;
    }

}
