package pt.lsts.imc;

public class FormationEvaluation extends IMCMessage {
    public static final int ID_STATIC = 823;


    public enum TYPE {
        REQUEST(0),
        REPORT(1);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public enum OP {
        START(0),
        STOP(1),
        READY(2),
        EXECUTING(3),
        FAILURE(4);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public FormationEvaluation() {
        super(ID_STATIC);
    }

    public FormationEvaluation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormationEvaluation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormationEvaluation create(Object... values) {
        FormationEvaluation m = new FormationEvaluation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormationEvaluation clone(IMCMessage msg) throws Exception {
        FormationEvaluation m = new FormationEvaluation();
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

    public TYPE getType() {
        try {
            TYPE o = TYPE.valueOf(getMessageType().getFieldPossibleValues("type").get(getLong("type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTypeStr() {
        return getString("type");
    }

    public short getTypeVal() {
        return getShort("type");
    }

    public FormationEvaluation setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public FormationEvaluation setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public FormationEvaluation setType(TYPE type) {
        values.put("type", type.value());
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

    public FormationEvaluation setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public FormationEvaluation setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public FormationEvaluation setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getErrMean() {
        return getDouble("err_mean");
    }

    public FormationEvaluation setErrMean(double err_mean) {
        values.put("err_mean", err_mean);
        return this;
    }

    public double getDistMinAbs() {
        return getDouble("dist_min_abs");
    }

    public FormationEvaluation setDistMinAbs(double dist_min_abs) {
        values.put("dist_min_abs", dist_min_abs);
        return this;
    }

    public double getDistMinMean() {
        return getDouble("dist_min_mean");
    }

    public FormationEvaluation setDistMinMean(double dist_min_mean) {
        values.put("dist_min_mean", dist_min_mean);
        return this;
    }

    public double getRollRateMean() {
        return getDouble("roll_rate_mean");
    }

    public FormationEvaluation setRollRateMean(double roll_rate_mean) {
        values.put("roll_rate_mean", roll_rate_mean);
        return this;
    }

    public double getTime() {
        return getDouble("time");
    }

    public FormationEvaluation setTime(double time) {
        values.put("time", time);
        return this;
    }

    public FormationControlParams getControlparams() {
        return getMessageOrNull(FormationControlParams.class, "FormationControlParams");
    }

    public <T extends IMCMessage> T getControlparams(Class<T> clazz) throws Exception {
        return getMessage(clazz, "ControlParams");
    }

    public FormationEvaluation setControlparams(FormationControlParams ControlParams) {
        values.put("ControlParams", ControlParams);
        return this;
    }

}
