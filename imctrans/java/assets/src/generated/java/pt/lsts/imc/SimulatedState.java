package pt.lsts.imc;

public class SimulatedState extends IMCMessage {
    public static final int ID_STATIC = 50;


    public SimulatedState() {
        super(ID_STATIC);
    }

    public SimulatedState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SimulatedState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimulatedState create(Object... values) {
        SimulatedState m = new SimulatedState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SimulatedState clone(IMCMessage msg) throws Exception {
        SimulatedState m = new SimulatedState();
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

    public double getLat() {
        return getDouble("lat");
    }

    public SimulatedState setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public SimulatedState setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getHeight() {
        return getDouble("height");
    }

    public SimulatedState setHeight(double height) {
        values.put("height", height);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public SimulatedState setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public SimulatedState setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public SimulatedState setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public SimulatedState setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public SimulatedState setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public SimulatedState setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

    public double getU() {
        return getDouble("u");
    }

    public SimulatedState setU(double u) {
        values.put("u", u);
        return this;
    }

    public double getV() {
        return getDouble("v");
    }

    public SimulatedState setV(double v) {
        values.put("v", v);
        return this;
    }

    public double getW() {
        return getDouble("w");
    }

    public SimulatedState setW(double w) {
        values.put("w", w);
        return this;
    }

    public double getP() {
        return getDouble("p");
    }

    public SimulatedState setP(double p) {
        values.put("p", p);
        return this;
    }

    public double getQ() {
        return getDouble("q");
    }

    public SimulatedState setQ(double q) {
        values.put("q", q);
        return this;
    }

    public double getR() {
        return getDouble("r");
    }

    public SimulatedState setR(double r) {
        values.put("r", r);
        return this;
    }

    public double getSvx() {
        return getDouble("svx");
    }

    public SimulatedState setSvx(double svx) {
        values.put("svx", svx);
        return this;
    }

    public double getSvy() {
        return getDouble("svy");
    }

    public SimulatedState setSvy(double svy) {
        values.put("svy", svy);
        return this;
    }

    public double getSvz() {
        return getDouble("svz");
    }

    public SimulatedState setSvz(double svz) {
        values.put("svz", svz);
        return this;
    }

}
