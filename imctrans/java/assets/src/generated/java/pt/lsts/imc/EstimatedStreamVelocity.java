package pt.lsts.imc;

public class EstimatedStreamVelocity extends IMCMessage {
    public static final int ID_STATIC = 351;


    public EstimatedStreamVelocity() {
        super(ID_STATIC);
    }

    public EstimatedStreamVelocity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EstimatedStreamVelocity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EstimatedStreamVelocity create(Object... values) {
        EstimatedStreamVelocity m = new EstimatedStreamVelocity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EstimatedStreamVelocity clone(IMCMessage msg) throws Exception {
        EstimatedStreamVelocity m = new EstimatedStreamVelocity();
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

    public double getX() {
        return getDouble("x");
    }

    public EstimatedStreamVelocity setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public EstimatedStreamVelocity setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public EstimatedStreamVelocity setZ(double z) {
        values.put("z", z);
        return this;
    }

}
