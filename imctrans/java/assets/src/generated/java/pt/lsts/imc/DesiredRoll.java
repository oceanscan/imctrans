package pt.lsts.imc;

public class DesiredRoll extends ControlCommand {
    public static final int ID_STATIC = 403;


    public DesiredRoll() {
        super(ID_STATIC);
    }

    public DesiredRoll(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredRoll(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredRoll create(Object... values) {
        DesiredRoll m = new DesiredRoll();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredRoll clone(IMCMessage msg) throws Exception {
        DesiredRoll m = new DesiredRoll();
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

    public DesiredRoll setValue(double value) {
        values.put("value", value);
        return this;
    }

}
