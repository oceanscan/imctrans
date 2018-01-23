package pt.lsts.imc;

public class VehicleFormationParticipant extends IMCMessage {
    public static final int ID_STATIC = 467;


    public VehicleFormationParticipant() {
        super(ID_STATIC);
    }

    public VehicleFormationParticipant(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VehicleFormationParticipant(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VehicleFormationParticipant create(Object... values) {
        VehicleFormationParticipant m = new VehicleFormationParticipant();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VehicleFormationParticipant clone(IMCMessage msg) throws Exception {
        VehicleFormationParticipant m = new VehicleFormationParticipant();
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

    public int getVid() {
        return getInteger("vid");
    }

    public VehicleFormationParticipant setVid(int vid) {
        values.put("vid", vid);
        return this;
    }

    public double getOffX() {
        return getDouble("off_x");
    }

    public VehicleFormationParticipant setOffX(double off_x) {
        values.put("off_x", off_x);
        return this;
    }

    public double getOffY() {
        return getDouble("off_y");
    }

    public VehicleFormationParticipant setOffY(double off_y) {
        values.put("off_y", off_y);
        return this;
    }

    public double getOffZ() {
        return getDouble("off_z");
    }

    public VehicleFormationParticipant setOffZ(double off_z) {
        values.put("off_z", off_z);
        return this;
    }

}
