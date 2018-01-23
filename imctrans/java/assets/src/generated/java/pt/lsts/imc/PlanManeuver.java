package pt.lsts.imc;

public class PlanManeuver extends IMCMessage {
    public static final int ID_STATIC = 552;


    public PlanManeuver() {
        super(ID_STATIC);
    }

    public PlanManeuver(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanManeuver(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanManeuver create(Object... values) {
        PlanManeuver m = new PlanManeuver();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanManeuver clone(IMCMessage msg) throws Exception {
        PlanManeuver m = new PlanManeuver();
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

    public String getManeuverId() {
        return getString("maneuver_id");
    }

    public PlanManeuver setManeuverId(String maneuver_id) {
        values.put("maneuver_id", maneuver_id);
        return this;
    }

    public Maneuver getData() {
        return getMessageOrNull(Maneuver.class, "Maneuver");
    }

    public <T extends IMCMessage> T getData(Class<T> clazz) throws Exception {
        return getMessage(clazz, "data");
    }

    public PlanManeuver setData(Maneuver data) {
        values.put("data", data);
        return this;
    }

    public java.util.Vector<IMCMessage> getStartActions() {
        return getMessageListOrNull("start_actions", IMCMessage.class);
    }

    public PlanManeuver setStartActions(java.util.Collection<IMCMessage> start_actions) {
        values.put("start_actions", start_actions);
        return this;
    }

    public java.util.Vector<IMCMessage> getEndActions() {
        return getMessageListOrNull("end_actions", IMCMessage.class);
    }

    public PlanManeuver setEndActions(java.util.Collection<IMCMessage> end_actions) {
        values.put("end_actions", end_actions);
        return this;
    }

}
