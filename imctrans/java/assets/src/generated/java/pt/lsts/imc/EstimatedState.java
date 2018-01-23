package pt.lsts.imc;

public class EstimatedState extends IMCMessage {
    public static final int ID_STATIC = 350;


    public EstimatedState() {
        super(ID_STATIC);
    }

    public EstimatedState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EstimatedState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EstimatedState create(Object... values) {
        EstimatedState m = new EstimatedState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EstimatedState clone(IMCMessage msg) throws Exception {
        EstimatedState m = new EstimatedState();
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

    public EstimatedState setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public EstimatedState setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getHeight() {
        return getDouble("height");
    }

    public EstimatedState setHeight(double height) {
        values.put("height", height);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public EstimatedState setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public EstimatedState setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public EstimatedState setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public EstimatedState setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public EstimatedState setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public EstimatedState setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

    public double getU() {
        return getDouble("u");
    }

    public EstimatedState setU(double u) {
        values.put("u", u);
        return this;
    }

    public double getV() {
        return getDouble("v");
    }

    public EstimatedState setV(double v) {
        values.put("v", v);
        return this;
    }

    public double getW() {
        return getDouble("w");
    }

    public EstimatedState setW(double w) {
        values.put("w", w);
        return this;
    }

    public double getVx() {
        return getDouble("vx");
    }

    public EstimatedState setVx(double vx) {
        values.put("vx", vx);
        return this;
    }

    public double getVy() {
        return getDouble("vy");
    }

    public EstimatedState setVy(double vy) {
        values.put("vy", vy);
        return this;
    }

    public double getVz() {
        return getDouble("vz");
    }

    public EstimatedState setVz(double vz) {
        values.put("vz", vz);
        return this;
    }

    public double getP() {
        return getDouble("p");
    }

    public EstimatedState setP(double p) {
        values.put("p", p);
        return this;
    }

    public double getQ() {
        return getDouble("q");
    }

    public EstimatedState setQ(double q) {
        values.put("q", q);
        return this;
    }

    public double getR() {
        return getDouble("r");
    }

    public EstimatedState setR(double r) {
        values.put("r", r);
        return this;
    }

    public double getDepth() {
        return getDouble("depth");
    }

    public EstimatedState setDepth(double depth) {
        values.put("depth", depth);
        return this;
    }

    public double getAlt() {
        return getDouble("alt");
    }

    public EstimatedState setAlt(double alt) {
        values.put("alt", alt);
        return this;
    }

}
