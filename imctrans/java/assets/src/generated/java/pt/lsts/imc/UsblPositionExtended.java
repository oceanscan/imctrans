package pt.lsts.imc;

public class UsblPositionExtended extends IMCMessage {
    public static final int ID_STATIC = 899;


    public UsblPositionExtended() {
        super(ID_STATIC);
    }

    public UsblPositionExtended(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblPositionExtended(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblPositionExtended create(Object... values) {
        UsblPositionExtended m = new UsblPositionExtended();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblPositionExtended clone(IMCMessage msg) throws Exception {
        UsblPositionExtended m = new UsblPositionExtended();
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

    public UsblPositionExtended setTarget(String target) {
        values.put("target", target);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public UsblPositionExtended setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public UsblPositionExtended setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public UsblPositionExtended setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getN() {
        return getDouble("n");
    }

    public UsblPositionExtended setN(double n) {
        values.put("n", n);
        return this;
    }

    public double getE() {
        return getDouble("e");
    }

    public UsblPositionExtended setE(double e) {
        values.put("e", e);
        return this;
    }

    public double getD() {
        return getDouble("d");
    }

    public UsblPositionExtended setD(double d) {
        values.put("d", d);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public UsblPositionExtended setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public UsblPositionExtended setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public UsblPositionExtended setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

    public double getAccuracy() {
        return getDouble("accuracy");
    }

    public UsblPositionExtended setAccuracy(double accuracy) {
        values.put("accuracy", accuracy);
        return this;
    }

}
