package pt.lsts.imc;

public class EntityInfo extends IMCMessage {
    public static final int ID_STATIC = 3;


    public EntityInfo() {
        super(ID_STATIC);
    }

    public EntityInfo(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EntityInfo(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityInfo create(Object... values) {
        EntityInfo m = new EntityInfo();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EntityInfo clone(IMCMessage msg) throws Exception {
        EntityInfo m = new EntityInfo();
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

    public short getId() {
        return getShort("id");
    }

    public EntityInfo setId(short id) {
        values.put("id", id);
        return this;
    }

    public String getLabel() {
        return getString("label");
    }

    public EntityInfo setLabel(String label) {
        values.put("label", label);
        return this;
    }

    public String getComponent() {
        return getString("component");
    }

    public EntityInfo setComponent(String component) {
        values.put("component", component);
        return this;
    }

    public int getActTime() {
        return getInteger("act_time");
    }

    public EntityInfo setActTime(int act_time) {
        values.put("act_time", act_time);
        return this;
    }

    public int getDeactTime() {
        return getInteger("deact_time");
    }

    public EntityInfo setDeactTime(int deact_time) {
        values.put("deact_time", deact_time);
        return this;
    }

}
