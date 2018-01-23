package pt.lsts.imc;

public class DissolvedOxygen extends IMCMessage {
    public static final int ID_STATIC = 295;


    public DissolvedOxygen() {
        super(ID_STATIC);
    }

    public DissolvedOxygen(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DissolvedOxygen(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DissolvedOxygen create(Object... values) {
        DissolvedOxygen m = new DissolvedOxygen();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DissolvedOxygen clone(IMCMessage msg) throws Exception {
        DissolvedOxygen m = new DissolvedOxygen();
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

    public DissolvedOxygen setValue(double value) {
        values.put("value", value);
        return this;
    }

}
