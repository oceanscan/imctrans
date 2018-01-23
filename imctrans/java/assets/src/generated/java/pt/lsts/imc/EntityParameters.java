package pt.lsts.imc;

public class EntityParameters extends IMCMessage {
    public static final int ID_STATIC = 802;


    public EntityParameters() {
        super(ID_STATIC);
    }

    public EntityParameters(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EntityParameters(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityParameters create(Object... values) {
        EntityParameters m = new EntityParameters();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EntityParameters clone(IMCMessage msg) throws Exception {
        EntityParameters m = new EntityParameters();
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

    public String getName() {
        return getString("name");
    }

    public EntityParameters setName(String name) {
        values.put("name", name);
        return this;
    }

    public java.util.Vector<EntityParameter> getParams() {
        return getMessageListOrNull("params", EntityParameter.class);
    }

    public EntityParameters setParams(java.util.Collection<EntityParameter> params) {
        values.put("params", params);
        return this;
    }

}
