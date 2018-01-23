package pt.lsts.imc;

public class Temperature extends IMCMessage {
    public static final int ID_STATIC = 263;


    public Temperature() {
        super(ID_STATIC);
    }

    public Temperature(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Temperature(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Temperature create(Object... values) {
        Temperature m = new Temperature();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Temperature clone(IMCMessage msg) throws Exception {
        Temperature m = new Temperature();
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

    public Temperature setValue(double value) {
        values.put("value", value);
        return this;
    }

}
