package pt.lsts.imc;

public class PlanControlState extends IMCMessage {
    public static final int ID_STATIC = 560;


    public enum STATE {
        BLOCKED(0),
        READY(1),
        INITIALIZING(2),
        EXECUTING(3);

        protected long value;

        public long value() {
            return value;
        }

        STATE(long value) {
            this.value = value;
        }
    }

    public enum LAST_OUTCOME {
        NONE(0),
        SUCCESS(1),
        FAILURE(2);

        protected long value;

        public long value() {
            return value;
        }

        LAST_OUTCOME(long value) {
            this.value = value;
        }
    }

    public PlanControlState() {
        super(ID_STATIC);
    }

    public PlanControlState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanControlState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanControlState create(Object... values) {
        PlanControlState m = new PlanControlState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanControlState clone(IMCMessage msg) throws Exception {
        PlanControlState m = new PlanControlState();
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

    public STATE getState() {
        try {
            STATE o = STATE.valueOf(getMessageType().getFieldPossibleValues("state").get(getLong("state")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getStateStr() {
        return getString("state");
    }

    public short getStateVal() {
        return getShort("state");
    }

    public PlanControlState setStateStr(String state) {
        setValue("state", state);
        return this;
    }

    public PlanControlState setStateVal(STATE state) {
        setValue("state", state);
        return this;
    }

    public PlanControlState setState(STATE state) {
        values.put("state", state.value());
        return this;
    }

    public String getPlanId() {
        return getString("plan_id");
    }

    public PlanControlState setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public int getPlanEta() {
        return getInteger("plan_eta");
    }

    public PlanControlState setPlanEta(int plan_eta) {
        values.put("plan_eta", plan_eta);
        return this;
    }

    public double getPlanProgress() {
        return getDouble("plan_progress");
    }

    public PlanControlState setPlanProgress(double plan_progress) {
        values.put("plan_progress", plan_progress);
        return this;
    }

    public String getManId() {
        return getString("man_id");
    }

    public PlanControlState setManId(String man_id) {
        values.put("man_id", man_id);
        return this;
    }

    public int getManType() {
        return getInteger("man_type");
    }

    public PlanControlState setManType(int man_type) {
        values.put("man_type", man_type);
        return this;
    }

    public int getManEta() {
        return getInteger("man_eta");
    }

    public PlanControlState setManEta(int man_eta) {
        values.put("man_eta", man_eta);
        return this;
    }

    public LAST_OUTCOME getLastOutcome() {
        try {
            LAST_OUTCOME o = LAST_OUTCOME.valueOf(getMessageType().getFieldPossibleValues("last_outcome").get(getLong("last_outcome")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getLastOutcomeStr() {
        return getString("last_outcome");
    }

    public short getLastOutcomeVal() {
        return getShort("last_outcome");
    }

    public PlanControlState setLastOutcomeStr(String last_outcome) {
        setValue("last_outcome", last_outcome);
        return this;
    }

    public PlanControlState setLastOutcomeVal(LAST_OUTCOME last_outcome) {
        setValue("last_outcome", last_outcome);
        return this;
    }

    public PlanControlState setLastOutcome(LAST_OUTCOME last_outcome) {
        values.put("last_outcome", last_outcome.value());
        return this;
    }

}
