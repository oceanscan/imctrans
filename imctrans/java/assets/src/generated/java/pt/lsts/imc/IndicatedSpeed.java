package pt.lsts.imc;

public class IndicatedSpeed extends IMCMessage {
    public static final int ID_STATIC = 352;


    public IndicatedSpeed() {
        super(ID_STATIC);
    }

    public IndicatedSpeed(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public IndicatedSpeed(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IndicatedSpeed create(Object... values) {
        IndicatedSpeed m = new IndicatedSpeed();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static IndicatedSpeed clone(IMCMessage msg) throws Exception {
        IndicatedSpeed m = new IndicatedSpeed();
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

    public IndicatedSpeed setValue(double value) {
        values.put("value", value);
        return this;
    }

}
