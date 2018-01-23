package pt.lsts.imc;

public class PH extends IMCMessage {
    public static final int ID_STATIC = 298;


    public PH() {
        super(ID_STATIC);
    }

    public PH(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PH(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PH create(Object... values) {
        PH m = new PH();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PH clone(IMCMessage msg) throws Exception {
        PH m = new PH();
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

    public PH setValue(double value) {
        values.put("value", value);
        return this;
    }

}
