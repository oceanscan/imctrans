package pt.lsts.imc;

public class OpticalBackscatter extends IMCMessage {
    public static final int ID_STATIC = 904;


    public OpticalBackscatter() {
        super(ID_STATIC);
    }

    public OpticalBackscatter(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public OpticalBackscatter(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OpticalBackscatter create(Object... values) {
        OpticalBackscatter m = new OpticalBackscatter();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static OpticalBackscatter clone(IMCMessage msg) throws Exception {
        OpticalBackscatter m = new OpticalBackscatter();
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

    public OpticalBackscatter setValue(double value) {
        values.put("value", value);
        return this;
    }

}
