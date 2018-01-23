package pt.lsts.imc;

public class GroundVelocity extends IMCMessage {
    public static final int ID_STATIC = 259;

public static final short VAL_VEL_X = 0x01;
public static final short VAL_VEL_Y = 0x02;
public static final short VAL_VEL_Z = 0x04;

    public GroundVelocity() {
        super(ID_STATIC);
    }

    public GroundVelocity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GroundVelocity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GroundVelocity create(Object... values) {
        GroundVelocity m = new GroundVelocity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GroundVelocity clone(IMCMessage msg) throws Exception {
        GroundVelocity m = new GroundVelocity();
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

    public short getValidity() {
        return getShort("validity");
    }

    public GroundVelocity setValidity(short validity) {
        values.put("validity", validity);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public GroundVelocity setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public GroundVelocity setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public GroundVelocity setZ(double z) {
        values.put("z", z);
        return this;
    }

}
