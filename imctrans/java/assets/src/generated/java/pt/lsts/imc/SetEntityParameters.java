package pt.lsts.imc;

public class SetEntityParameters extends IMCMessage {
    public static final int ID_STATIC = 804;


    public SetEntityParameters() {
        super(ID_STATIC);
    }

    public SetEntityParameters(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SetEntityParameters(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SetEntityParameters create(Object... values) {
        SetEntityParameters m = new SetEntityParameters();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SetEntityParameters clone(IMCMessage msg) throws Exception {
        SetEntityParameters m = new SetEntityParameters();
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

    public SetEntityParameters setName(String name) {
        values.put("name", name);
        return this;
    }

    public java.util.Vector<EntityParameter> getParams() {
        return getMessageListOrNull("params", EntityParameter.class);
    }

    public SetEntityParameters setParams(java.util.Collection<EntityParameter> params) {
        values.put("params", params);
        return this;
    }

}
