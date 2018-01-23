package pt.lsts.imc;

public class RelativeHumidity extends IMCMessage {
    public static final int ID_STATIC = 272;


    public RelativeHumidity() {
        super(ID_STATIC);
    }

    public RelativeHumidity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RelativeHumidity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RelativeHumidity create(Object... values) {
        RelativeHumidity m = new RelativeHumidity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RelativeHumidity clone(IMCMessage msg) throws Exception {
        RelativeHumidity m = new RelativeHumidity();
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

    public RelativeHumidity setValue(double value) {
        values.put("value", value);
        return this;
    }

}
