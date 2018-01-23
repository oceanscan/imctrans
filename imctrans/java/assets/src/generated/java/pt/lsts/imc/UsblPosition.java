package pt.lsts.imc;

public class UsblPosition extends IMCMessage {
    public static final int ID_STATIC = 891;


    public UsblPosition() {
        super(ID_STATIC);
    }

    public UsblPosition(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblPosition(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblPosition create(Object... values) {
        UsblPosition m = new UsblPosition();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblPosition clone(IMCMessage msg) throws Exception {
        UsblPosition m = new UsblPosition();
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

    public int getTarget() {
        return getInteger("target");
    }

    public UsblPosition setTarget(int target) {
        values.put("target", target);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public UsblPosition setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public UsblPosition setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public UsblPosition setZ(double z) {
        values.put("z", z);
        return this;
    }

}
