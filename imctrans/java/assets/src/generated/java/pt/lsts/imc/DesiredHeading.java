package pt.lsts.imc;

public class DesiredHeading extends ControlCommand {
    public static final int ID_STATIC = 400;


    public DesiredHeading() {
        super(ID_STATIC);
    }

    public DesiredHeading(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredHeading(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredHeading create(Object... values) {
        DesiredHeading m = new DesiredHeading();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredHeading clone(IMCMessage msg) throws Exception {
        DesiredHeading m = new DesiredHeading();
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

    public DesiredHeading setValue(double value) {
        values.put("value", value);
        return this;
    }

}
