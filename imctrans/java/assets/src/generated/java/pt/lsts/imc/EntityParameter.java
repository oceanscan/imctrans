package pt.lsts.imc;

public class EntityParameter extends IMCMessage {
    public static final int ID_STATIC = 801;


    public EntityParameter() {
        super(ID_STATIC);
    }

    public EntityParameter(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EntityParameter(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityParameter create(Object... values) {
        EntityParameter m = new EntityParameter();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EntityParameter clone(IMCMessage msg) throws Exception {
        EntityParameter m = new EntityParameter();
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

    public EntityParameter setName(String name) {
        values.put("name", name);
        return this;
    }

    public String getValue() {
        return getString("value");
    }

    public EntityParameter setValue(String value) {
        values.put("value", value);
        return this;
    }

}
