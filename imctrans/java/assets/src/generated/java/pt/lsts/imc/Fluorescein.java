package pt.lsts.imc;

public class Fluorescein extends IMCMessage {
    public static final int ID_STATIC = 290;


    public Fluorescein() {
        super(ID_STATIC);
    }

    public Fluorescein(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Fluorescein(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Fluorescein create(Object... values) {
        Fluorescein m = new Fluorescein();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Fluorescein clone(IMCMessage msg) throws Exception {
        Fluorescein m = new Fluorescein();
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

    public Fluorescein setValue(double value) {
        values.put("value", value);
        return this;
    }

}
