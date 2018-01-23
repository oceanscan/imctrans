package pt.lsts.imc;

public class UsblAngles extends IMCMessage {
    public static final int ID_STATIC = 890;


    public UsblAngles() {
        super(ID_STATIC);
    }

    public UsblAngles(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblAngles(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblAngles create(Object... values) {
        UsblAngles m = new UsblAngles();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblAngles clone(IMCMessage msg) throws Exception {
        UsblAngles m = new UsblAngles();
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

    public UsblAngles setTarget(int target) {
        values.put("target", target);
        return this;
    }

    public double getBearing() {
        return getDouble("bearing");
    }

    public UsblAngles setBearing(double bearing) {
        values.put("bearing", bearing);
        return this;
    }

    public double getElevation() {
        return getDouble("elevation");
    }

    public UsblAngles setElevation(double elevation) {
        values.put("elevation", elevation);
        return this;
    }

}
