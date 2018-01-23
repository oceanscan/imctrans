package pt.lsts.imc;

public class PlanSpecification extends IMCMessage {
    public static final int ID_STATIC = 551;


    public PlanSpecification() {
        super(ID_STATIC);
    }

    public PlanSpecification(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanSpecification(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanSpecification create(Object... values) {
        PlanSpecification m = new PlanSpecification();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanSpecification clone(IMCMessage msg) throws Exception {
        PlanSpecification m = new PlanSpecification();
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

    public String getPlanId() {
        return getString("plan_id");
    }

    public PlanSpecification setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public String getDescription() {
        return getString("description");
    }

    public PlanSpecification setDescription(String description) {
        values.put("description", description);
        return this;
    }

    public String getVnamespace() {
        return getString("vnamespace");
    }

    public PlanSpecification setVnamespace(String vnamespace) {
        values.put("vnamespace", vnamespace);
        return this;
    }

    public java.util.Vector<PlanVariable> getVariables() {
        return getMessageListOrNull("variables", PlanVariable.class);
    }

    public PlanSpecification setVariables(java.util.Collection<PlanVariable> variables) {
        values.put("variables", variables);
        return this;
    }

    public String getStartManId() {
        return getString("start_man_id");
    }

    public PlanSpecification setStartManId(String start_man_id) {
        values.put("start_man_id", start_man_id);
        return this;
    }

    public java.util.Vector<PlanManeuver> getManeuvers() {
        return getMessageListOrNull("maneuvers", PlanManeuver.class);
    }

    public PlanSpecification setManeuvers(java.util.Collection<PlanManeuver> maneuvers) {
        values.put("maneuvers", maneuvers);
        return this;
    }

    public java.util.Vector<PlanTransition> getTransitions() {
        return getMessageListOrNull("transitions", PlanTransition.class);
    }

    public PlanSpecification setTransitions(java.util.Collection<PlanTransition> transitions) {
        values.put("transitions", transitions);
        return this;
    }

    public java.util.Vector<IMCMessage> getStartActions() {
        return getMessageListOrNull("start_actions", IMCMessage.class);
    }

    public PlanSpecification setStartActions(java.util.Collection<IMCMessage> start_actions) {
        values.put("start_actions", start_actions);
        return this;
    }

    public java.util.Vector<IMCMessage> getEndActions() {
        return getMessageListOrNull("end_actions", IMCMessage.class);
    }

    public PlanSpecification setEndActions(java.util.Collection<IMCMessage> end_actions) {
        values.put("end_actions", end_actions);
        return this;
    }

}
