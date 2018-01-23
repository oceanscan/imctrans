package pt.lsts.imc;

public class Calibration extends IMCMessage {
    public static final int ID_STATIC = 506;


    public Calibration() {
        super(ID_STATIC);
    }

    public Calibration(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Calibration(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Calibration create(Object... values) {
        Calibration m = new Calibration();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Calibration clone(IMCMessage msg) throws Exception {
        Calibration m = new Calibration();
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

    public int getDuration() {
        return getInteger("duration");
    }

    public Calibration setDuration(int duration) {
        values.put("duration", duration);
        return this;
    }

}
