package pt.lsts.imc;

public class AirSaturation extends IMCMessage {
    public static final int ID_STATIC = 296;


    public AirSaturation() {
        super(ID_STATIC);
    }

    public AirSaturation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AirSaturation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AirSaturation create(Object... values) {
        AirSaturation m = new AirSaturation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AirSaturation clone(IMCMessage msg) throws Exception {
        AirSaturation m = new AirSaturation();
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

    public AirSaturation setValue(double value) {
        values.put("value", value);
        return this;
    }

}
