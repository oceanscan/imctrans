package pt.lsts.imc;

public class EulerAngles extends IMCMessage {
    public static final int ID_STATIC = 254;


    public EulerAngles() {
        super(ID_STATIC);
    }

    public EulerAngles(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EulerAngles(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EulerAngles create(Object... values) {
        EulerAngles m = new EulerAngles();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EulerAngles clone(IMCMessage msg) throws Exception {
        EulerAngles m = new EulerAngles();
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

    public double getTime() {
        return getDouble("time");
    }

    public EulerAngles setTime(double time) {
        values.put("time", time);
        return this;
    }

    public double getPhi() {
        return getDouble("phi");
    }

    public EulerAngles setPhi(double phi) {
        values.put("phi", phi);
        return this;
    }

    public double getTheta() {
        return getDouble("theta");
    }

    public EulerAngles setTheta(double theta) {
        values.put("theta", theta);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public EulerAngles setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

    public double getPsiMagnetic() {
        return getDouble("psi_magnetic");
    }

    public EulerAngles setPsiMagnetic(double psi_magnetic) {
        values.put("psi_magnetic", psi_magnetic);
        return this;
    }

}
