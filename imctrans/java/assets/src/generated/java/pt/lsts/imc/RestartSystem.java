package pt.lsts.imc;

public class RestartSystem extends IMCMessage {
    public static final int ID_STATIC = 9;


    public RestartSystem() {
        super(ID_STATIC);
    }

    public RestartSystem(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RestartSystem(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RestartSystem create(Object... values) {
        RestartSystem m = new RestartSystem();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RestartSystem clone(IMCMessage msg) throws Exception {
        RestartSystem m = new RestartSystem();
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

}
