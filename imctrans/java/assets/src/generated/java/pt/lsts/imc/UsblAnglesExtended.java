package pt.lsts.imc;

public class UsblAnglesExtended extends IMCMessage {
    public static final int ID_STATIC = 898;


    public UsblAnglesExtended() {
        super(ID_STATIC);
    }

    public UsblAnglesExtended(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblAnglesExtended(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblAnglesExtended create(Object... values) {
        UsblAnglesExtended m = new UsblAnglesExtended();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblAnglesExtended clone(IMCMessage msg) throws Exception {
        UsblAnglesExtended m = new UsblAnglesExtended();
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

    public String getTarget() {
        return getString("target");
    }

    public UsblAnglesExtended setTarget(String target) {
        values.put("target", target);
        return this;
    }

    public double getLbearing() {
        return getDouble("lbearing");
    }

    public UsblAnglesExtended setLbearing(double lbearing) {
        values.put("lbearing", lbearing);
        return this;
    }

    public double getLelevation() {
        return getDouble("lelevation");
    }

    public UsblAnglesExtended setLelevation(double lelevation) {
        values.put("lelevation", lelevation);
        return this;
    }

    public double getBearing() {
        return getDouble("bearing");
    }

    public UsblAnglesExtended setBearing(double bearing) {
        values.put("bearing", bearing);
        return this;
    }

    public double getElevation() {
        return getDouble("elevation");
    }

    public UsblAnglesExtended setElevation(double elevation) {
        values.put("elevation", elevation);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public UsblAnglesExtended setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public UsblAnglesExtended setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public UsblAnglesExtended setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

    public double getAccuracy() {
        return getDouble("accuracy");
    }

    public UsblAnglesExtended setAccuracy(double accuracy) {
        values.put("accuracy", accuracy);
        return this;
    }

}
