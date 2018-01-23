package pt.lsts.imc;

public class LeaderState extends IMCMessage {
    public static final int ID_STATIC = 563;


    public enum OP {
        REQUEST(0),
        SET(1),
        REPORT(2);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public LeaderState() {
        super(ID_STATIC);
    }

    public LeaderState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LeaderState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LeaderState create(Object... values) {
        LeaderState m = new LeaderState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LeaderState clone(IMCMessage msg) throws Exception {
        LeaderState m = new LeaderState();
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

    public String getGroupName() {
        return getString("group_name");
    }

    public LeaderState setGroupName(String group_name) {
        values.put("group_name", group_name);
        return this;
    }

    public OP getOp() {
        try {
            OP o = OP.valueOf(getMessageType().getFieldPossibleValues("op").get(getLong("op")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getOpStr() {
        return getString("op");
    }

    public short getOpVal() {
        return getShort("op");
    }

    public LeaderState setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public LeaderState setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public LeaderState setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public LeaderState setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public LeaderState setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getHeight() {
        return getDouble("height");
    }

    public LeaderState setHeight(double height) {
        values.put("height", height);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public LeaderState setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public LeaderState setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public LeaderState setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public LeaderState setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public LeaderState setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public LeaderState setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

    public double getVx() {
        return getDouble("vx");
    }

    public LeaderState setVx(double vx) {
        values.put("vx", vx);
        return this;
    }

    public double getVy() {
        return getDouble("vy");
    }

    public LeaderState setVy(double vy) {
        values.put("vy", vy);
        return this;
    }

    public double getVz() {
        return getDouble("vz");
    }

    public LeaderState setVz(double vz) {
        values.put("vz", vz);
        return this;
    }

    public double getP() {
        return getDouble("p");
    }

    public LeaderState setP(double p) {
        values.put("p", p);
        return this;
    }

    public double getQ() {
        return getDouble("q");
    }

    public LeaderState setQ(double q) {
        values.put("q", q);
        return this;
    }

    public double getR() {
        return getDouble("r");
    }

    public LeaderState setR(double r) {
        values.put("r", r);
        return this;
    }

    public double getSvx() {
        return getDouble("svx");
    }

    public LeaderState setSvx(double svx) {
        values.put("svx", svx);
        return this;
    }

    public double getSvy() {
        return getDouble("svy");
    }

    public LeaderState setSvy(double svy) {
        values.put("svy", svy);
        return this;
    }

    public double getSvz() {
        return getDouble("svz");
    }

    public LeaderState setSvz(double svz) {
        values.put("svz", svz);
        return this;
    }

}
