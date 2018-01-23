package pt.lsts.imc;

public class SoundSpeed extends IMCMessage {
    public static final int ID_STATIC = 267;


    public SoundSpeed() {
        super(ID_STATIC);
    }

    public SoundSpeed(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SoundSpeed(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SoundSpeed create(Object... values) {
        SoundSpeed m = new SoundSpeed();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SoundSpeed clone(IMCMessage msg) throws Exception {
        SoundSpeed m = new SoundSpeed();
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

    public SoundSpeed setValue(double value) {
        values.put("value", value);
        return this;
    }

}
