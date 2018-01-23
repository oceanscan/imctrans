package pt.lsts.imc;

public class Redox extends IMCMessage {
    public static final int ID_STATIC = 299;


    public Redox() {
        super(ID_STATIC);
    }

    public Redox(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Redox(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Redox create(Object... values) {
        Redox m = new Redox();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Redox clone(IMCMessage msg) throws Exception {
        Redox m = new Redox();
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

    public Redox setValue(double value) {
        values.put("value", value);
        return this;
    }

}
