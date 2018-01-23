package pt.lsts.imc;

public class RemoteActions extends IMCMessage {
    public static final int ID_STATIC = 305;


    public RemoteActions() {
        super(ID_STATIC);
    }

    public RemoteActions(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RemoteActions(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RemoteActions create(Object... values) {
        RemoteActions m = new RemoteActions();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RemoteActions clone(IMCMessage msg) throws Exception {
        RemoteActions m = new RemoteActions();
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

    public java.util.LinkedHashMap<String, String> getActions() {
        return getTupleList("actions");
    }

    public RemoteActions setActions(java.util.LinkedHashMap<String, ?> actions) {
        return setActions(actions);
    }

    public RemoteActions setActions(String actions) {
        values.put("actions", actions);
        return this;
    }

}
