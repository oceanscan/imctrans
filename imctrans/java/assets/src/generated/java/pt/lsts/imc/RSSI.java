package pt.lsts.imc;

public class RSSI extends IMCMessage {
    public static final int ID_STATIC = 153;


    public RSSI() {
        super(ID_STATIC);
    }

    public RSSI(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RSSI(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RSSI create(Object... values) {
        RSSI m = new RSSI();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RSSI clone(IMCMessage msg) throws Exception {
        RSSI m = new RSSI();
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

    public RSSI setValue(double value) {
        values.put("value", value);
        return this;
    }

}
