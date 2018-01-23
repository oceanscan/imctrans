package pt.lsts.imc;

public class FormationPlanExecution extends Maneuver {
    public static final int ID_STATIC = 477;


    public FormationPlanExecution() {
        super(ID_STATIC);
    }

    public FormationPlanExecution(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormationPlanExecution(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormationPlanExecution create(Object... values) {
        FormationPlanExecution m = new FormationPlanExecution();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormationPlanExecution clone(IMCMessage msg) throws Exception {
        FormationPlanExecution m = new FormationPlanExecution();
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

    public FormationPlanExecution setGroupName(String group_name) {
        values.put("group_name", group_name);
        return this;
    }

    public String getFormationName() {
        return getString("formation_name");
    }

    public FormationPlanExecution setFormationName(String formation_name) {
        values.put("formation_name", formation_name);
        return this;
    }

    public String getPlanId() {
        return getString("plan_id");
    }

    public FormationPlanExecution setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public String getDescription() {
        return getString("description");
    }

    public FormationPlanExecution setDescription(String description) {
        values.put("description", description);
        return this;
    }

    public double getLeaderSpeed() {
        return getDouble("leader_speed");
    }

    public FormationPlanExecution setLeaderSpeed(double leader_speed) {
        values.put("leader_speed", leader_speed);
        return this;
    }

    public double getLeaderBankLim() {
        return getDouble("leader_bank_lim");
    }

    public FormationPlanExecution setLeaderBankLim(double leader_bank_lim) {
        values.put("leader_bank_lim", leader_bank_lim);
        return this;
    }

    public double getPosSimErrLim() {
        return getDouble("pos_sim_err_lim");
    }

    public FormationPlanExecution setPosSimErrLim(double pos_sim_err_lim) {
        values.put("pos_sim_err_lim", pos_sim_err_lim);
        return this;
    }

    public double getPosSimErrWrn() {
        return getDouble("pos_sim_err_wrn");
    }

    public FormationPlanExecution setPosSimErrWrn(double pos_sim_err_wrn) {
        values.put("pos_sim_err_wrn", pos_sim_err_wrn);
        return this;
    }

    public int getPosSimErrTimeout() {
        return getInteger("pos_sim_err_timeout");
    }

    public FormationPlanExecution setPosSimErrTimeout(int pos_sim_err_timeout) {
        values.put("pos_sim_err_timeout", pos_sim_err_timeout);
        return this;
    }

    public double getConvergMax() {
        return getDouble("converg_max");
    }

    public FormationPlanExecution setConvergMax(double converg_max) {
        values.put("converg_max", converg_max);
        return this;
    }

    public int getConvergTimeout() {
        return getInteger("converg_timeout");
    }

    public FormationPlanExecution setConvergTimeout(int converg_timeout) {
        values.put("converg_timeout", converg_timeout);
        return this;
    }

    public int getCommsTimeout() {
        return getInteger("comms_timeout");
    }

    public FormationPlanExecution setCommsTimeout(int comms_timeout) {
        values.put("comms_timeout", comms_timeout);
        return this;
    }

    public double getTurbLim() {
        return getDouble("turb_lim");
    }

    public FormationPlanExecution setTurbLim(double turb_lim) {
        values.put("turb_lim", turb_lim);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public FormationPlanExecution setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public FormationPlanExecution setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
