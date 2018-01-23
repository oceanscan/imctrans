package pt.lsts.imc;

public class FollowReference extends Maneuver {
    public static final int ID_STATIC = 478;


    public FollowReference() {
        super(ID_STATIC);
    }

    public FollowReference(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FollowReference(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FollowReference create(Object... values) {
        FollowReference m = new FollowReference();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FollowReference clone(IMCMessage msg) throws Exception {
        FollowReference m = new FollowReference();
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

    public int getControlSrc() {
        return getInteger("control_src");
    }

    public FollowReference setControlSrc(int control_src) {
        values.put("control_src", control_src);
        return this;
    }

    public short getControlEnt() {
        return getShort("control_ent");
    }

    public FollowReference setControlEnt(short control_ent) {
        values.put("control_ent", control_ent);
        return this;
    }

    public double getTimeout() {
        return getDouble("timeout");
    }

    public FollowReference setTimeout(double timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getLoiterRadius() {
        return getDouble("loiter_radius");
    }

    public FollowReference setLoiterRadius(double loiter_radius) {
        values.put("loiter_radius", loiter_radius);
        return this;
    }

    public double getAltitudeInterval() {
        return getDouble("altitude_interval");
    }

    public FollowReference setAltitudeInterval(double altitude_interval) {
        values.put("altitude_interval", altitude_interval);
        return this;
    }

}
