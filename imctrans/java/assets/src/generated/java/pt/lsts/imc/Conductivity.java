package pt.lsts.imc;

public class Conductivity extends IMCMessage {
    public static final int ID_STATIC = 269;


    public Conductivity() {
        super(ID_STATIC);
    }

    public Conductivity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Conductivity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Conductivity create(Object... values) {
        Conductivity m = new Conductivity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Conductivity clone(IMCMessage msg) throws Exception {
        Conductivity m = new Conductivity();
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

    public Conductivity setValue(double value) {
        values.put("value", value);
        return this;
    }

}
