package pt.lsts.imc;

public class GroupStreamVelocity extends IMCMessage {
    public static final int ID_STATIC = 362;


    public GroupStreamVelocity() {
        super(ID_STATIC);
    }

    public GroupStreamVelocity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GroupStreamVelocity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GroupStreamVelocity create(Object... values) {
        GroupStreamVelocity m = new GroupStreamVelocity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GroupStreamVelocity clone(IMCMessage msg) throws Exception {
        GroupStreamVelocity m = new GroupStreamVelocity();
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

    public GroupStreamVelocity setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public GroupStreamVelocity setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public GroupStreamVelocity setZ(double z) {
        values.put("z", z);
        return this;
    }

}
