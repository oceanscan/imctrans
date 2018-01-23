package pt.lsts.imc;

public class Salinity extends IMCMessage {
    public static final int ID_STATIC = 270;


    public Salinity() {
        super(ID_STATIC);
    }

    public Salinity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Salinity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Salinity create(Object... values) {
        Salinity m = new Salinity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Salinity clone(IMCMessage msg) throws Exception {
        Salinity m = new Salinity();
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

    public Salinity setValue(double value) {
        values.put("value", value);
        return this;
    }

}
