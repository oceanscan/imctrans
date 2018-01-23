package pt.lsts.imc;

public class LblEstimate extends IMCMessage {
    public static final int ID_STATIC = 360;


    public LblEstimate() {
        super(ID_STATIC);
    }

    public LblEstimate(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LblEstimate(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LblEstimate create(Object... values) {
        LblEstimate m = new LblEstimate();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LblEstimate clone(IMCMessage msg) throws Exception {
        LblEstimate m = new LblEstimate();
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

    public LblBeacon getBeacon() {
        return getMessageOrNull(LblBeacon.class, "LblBeacon");
    }

    public <T extends IMCMessage> T getBeacon(Class<T> clazz) throws Exception {
        return getMessage(clazz, "beacon");
    }

    public LblEstimate setBeacon(LblBeacon beacon) {
        values.put("beacon", beacon);
        return this;
    }

    public double getX() {
        return getDouble("x");
    }

    public LblEstimate setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public LblEstimate setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getVarX() {
        return getDouble("var_x");
    }

    public LblEstimate setVarX(double var_x) {
        values.put("var_x", var_x);
        return this;
    }

    public double getVarY() {
        return getDouble("var_y");
    }

    public LblEstimate setVarY(double var_y) {
        values.put("var_y", var_y);
        return this;
    }

    public double getDistance() {
        return getDouble("distance");
    }

    public LblEstimate setDistance(double distance) {
        values.put("distance", distance);
        return this;
    }

}
