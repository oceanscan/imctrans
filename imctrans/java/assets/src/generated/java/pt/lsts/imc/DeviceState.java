package pt.lsts.imc;

public class DeviceState extends IMCMessage {
    public static final int ID_STATIC = 282;


    public DeviceState() {
        super(ID_STATIC);
    }

    public DeviceState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DeviceState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DeviceState create(Object... values) {
        DeviceState m = new DeviceState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DeviceState clone(IMCMessage msg) throws Exception {
        DeviceState m = new DeviceState();
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

    public DeviceState setX(double x) {
        values.put("x", x);
        return this;
    }

    public double getY() {
        return getDouble("y");
    }

    public DeviceState setY(double y) {
        values.put("y", y);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public DeviceState setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public DeviceState setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public DeviceState setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public DeviceState setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

}
