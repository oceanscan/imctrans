package pt.lsts.imc;

public class PlanTransition extends IMCMessage {
    public static final int ID_STATIC = 553;


    public PlanTransition() {
        super(ID_STATIC);
    }

    public PlanTransition(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanTransition(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanTransition create(Object... values) {
        PlanTransition m = new PlanTransition();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanTransition clone(IMCMessage msg) throws Exception {
        PlanTransition m = new PlanTransition();
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

    public String getSourceMan() {
        return getString("source_man");
    }

    public PlanTransition setSourceMan(String source_man) {
        values.put("source_man", source_man);
        return this;
    }

    public String getDestMan() {
        return getString("dest_man");
    }

    public PlanTransition setDestMan(String dest_man) {
        values.put("dest_man", dest_man);
        return this;
    }

    public String getConditions() {
        return getString("conditions");
    }

    public PlanTransition setConditions(String conditions) {
        values.put("conditions", conditions);
        return this;
    }

    public java.util.Vector<IMCMessage> getActions() {
        return getMessageListOrNull("actions", IMCMessage.class);
    }

    public PlanTransition setActions(java.util.Collection<IMCMessage> actions) {
        values.put("actions", actions);
        return this;
    }

}
