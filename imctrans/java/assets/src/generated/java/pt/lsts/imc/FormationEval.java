package pt.lsts.imc;

public class FormationEval extends IMCMessage {
    public static final int ID_STATIC = 821;


    public FormationEval() {
        super(ID_STATIC);
    }

    public FormationEval(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormationEval(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormationEval create(Object... values) {
        FormationEval m = new FormationEval();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormationEval clone(IMCMessage msg) throws Exception {
        FormationEval m = new FormationEval();
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

    public double getErrMean() {
        return getDouble("err_mean");
    }

    public FormationEval setErrMean(double err_mean) {
        values.put("err_mean", err_mean);
        return this;
    }

    public double getDistMinAbs() {
        return getDouble("dist_min_abs");
    }

    public FormationEval setDistMinAbs(double dist_min_abs) {
        values.put("dist_min_abs", dist_min_abs);
        return this;
    }

    public double getDistMinMean() {
        return getDouble("dist_min_mean");
    }

    public FormationEval setDistMinMean(double dist_min_mean) {
        values.put("dist_min_mean", dist_min_mean);
        return this;
    }

}
