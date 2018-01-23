package pt.lsts.imc;

public class WaterVelocity extends IMCMessage {
    public static final int ID_STATIC = 260;

public static final short VAL_VEL_X = 0x01;
public static final short VAL_VEL_Y = 0x02;
public static final short VAL_VEL_Z = 0x04;

    public WaterVelocity() {
        super(ID_STATIC);
    }

    public WaterVelocity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public WaterVelocity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WaterVelocity create(Object... values) {
        WaterVelocity m = new WaterVelocity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static WaterVelocity clone(IMCMessage msg) throws Exception {
        WaterVelocity m = new WaterVelocity();
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

    public WaterVelocity setValidity(short validity) {
        values.put("validity", validity);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public WaterVelocity setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public WaterVelocity setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public WaterVelocity setZ(double z) {
        values.put("z", z);
        return this;
    }

}
