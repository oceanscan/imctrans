package pt.lsts.imc;

public class AllocatedControlTorques extends IMCMessage {
    public static final int ID_STATIC = 411;


    public AllocatedControlTorques() {
        super(ID_STATIC);
    }

    public AllocatedControlTorques(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AllocatedControlTorques(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AllocatedControlTorques create(Object... values) {
        AllocatedControlTorques m = new AllocatedControlTorques();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AllocatedControlTorques clone(IMCMessage msg) throws Exception {
        AllocatedControlTorques m = new AllocatedControlTorques();
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

    public double getK() {
        return getDouble("k");
    }

    public AllocatedControlTorques setK(double k) {
        values.put("k", k);
        return this;
    }

    public double getM() {
        return getDouble("m");
    }

    public AllocatedControlTorques setM(double m) {
        values.put("m", m);
        return this;
    }

    public double getN() {
        return getDouble("n");
    }

    public AllocatedControlTorques setN(double n) {
        values.put("n", n);
        return this;
    }

}
