package pt.lsts.imc;

public class DesiredPitch extends ControlCommand {
    public static final int ID_STATIC = 404;


    public DesiredPitch() {
        super(ID_STATIC);
    }

    public DesiredPitch(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredPitch(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredPitch create(Object... values) {
        DesiredPitch m = new DesiredPitch();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredPitch clone(IMCMessage msg) throws Exception {
        DesiredPitch m = new DesiredPitch();
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

    public DesiredPitch setValue(double value) {
        values.put("value", value);
        return this;
    }

}
