package pt.lsts.imc;

public class SetControlSurfaceDeflection extends IMCMessage {
    public static final int ID_STATIC = 303;


    public SetControlSurfaceDeflection() {
        super(ID_STATIC);
    }

    public SetControlSurfaceDeflection(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SetControlSurfaceDeflection(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SetControlSurfaceDeflection create(Object... values) {
        SetControlSurfaceDeflection m = new SetControlSurfaceDeflection();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SetControlSurfaceDeflection clone(IMCMessage msg) throws Exception {
        SetControlSurfaceDeflection m = new SetControlSurfaceDeflection();
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

    public short getId() {
        return getShort("id");
    }

    public SetControlSurfaceDeflection setId(short id) {
        values.put("id", id);
        return this;
    }

    public double getAngle() {
        return getDouble("angle");
    }

    public SetControlSurfaceDeflection setAngle(double angle) {
        values.put("angle", angle);
        return this;
    }

}
