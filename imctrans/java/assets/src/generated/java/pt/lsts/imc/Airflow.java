package pt.lsts.imc;

public class Airflow extends IMCMessage {
    public static final int ID_STATIC = 363;


    public Airflow() {
        super(ID_STATIC);
    }

    public Airflow(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Airflow(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Airflow create(Object... values) {
        Airflow m = new Airflow();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Airflow clone(IMCMessage msg) throws Exception {
        Airflow m = new Airflow();
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

    public double getVa() {
        return getDouble("va");
    }

    public Airflow setVa(double va) {
        values.put("va", va);
        return this;
    }

    public double getAoa() {
        return getDouble("aoa");
    }

    public Airflow setAoa(double aoa) {
        values.put("aoa", aoa);
        return this;
    }

    public double getSsa() {
        return getDouble("ssa");
    }

    public Airflow setSsa(double ssa) {
        values.put("ssa", ssa);
        return this;
    }

}
