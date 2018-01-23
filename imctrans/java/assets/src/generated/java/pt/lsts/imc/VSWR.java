package pt.lsts.imc;

public class VSWR extends IMCMessage {
    public static final int ID_STATIC = 154;


    public VSWR() {
        super(ID_STATIC);
    }

    public VSWR(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VSWR(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VSWR create(Object... values) {
        VSWR m = new VSWR();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VSWR clone(IMCMessage msg) throws Exception {
        VSWR m = new VSWR();
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

    public VSWR setValue(double value) {
        values.put("value", value);
        return this;
    }

}
