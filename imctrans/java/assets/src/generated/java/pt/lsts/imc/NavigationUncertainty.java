package pt.lsts.imc;

public class NavigationUncertainty extends IMCMessage {
    public static final int ID_STATIC = 354;


    public NavigationUncertainty() {
        super(ID_STATIC);
    }

    public NavigationUncertainty(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public NavigationUncertainty(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NavigationUncertainty create(Object... values) {
        NavigationUncertainty m = new NavigationUncertainty();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static NavigationUncertainty clone(IMCMessage msg) throws Exception {
        NavigationUncertainty m = new NavigationUncertainty();
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

    public NavigationUncertainty setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public NavigationUncertainty setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public NavigationUncertainty setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public NavigationUncertainty setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public NavigationUncertainty setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public NavigationUncertainty setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

    public double getP() {
        return getDouble("p");
    }

    public NavigationUncertainty setP(double p) {
        values.put("p", p);
        return this;
    }

    public double getQ() {
        return getDouble("q");
    }

    public NavigationUncertainty setQ(double q) {
        values.put("q", q);
        return this;
    }

    public double getR() {
        return getDouble("r");
    }

    public NavigationUncertainty setR(double r) {
        values.put("r", r);
        return this;
    }

    public double getU() {
        return getDouble("u");
    }

    public NavigationUncertainty setU(double u) {
        values.put("u", u);
        return this;
    }

    public double getV() {
        return getDouble("v");
    }

    public NavigationUncertainty setV(double v) {
        values.put("v", v);
        return this;
    }

    public double getW() {
        return getDouble("w");
    }

    public NavigationUncertainty setW(double w) {
        values.put("w", w);
        return this;
    }

    public double getBiasPsi() {
        return getDouble("bias_psi");
    }

    public NavigationUncertainty setBiasPsi(double bias_psi) {
        values.put("bias_psi", bias_psi);
        return this;
    }

    public double getBiasR() {
        return getDouble("bias_r");
    }

    public NavigationUncertainty setBiasR(double bias_r) {
        values.put("bias_r", bias_r);
        return this;
    }

}
