package pt.lsts.imc;

public class CloseSession extends IMCMessage {
    public static final int ID_STATIC = 807;


    public CloseSession() {
        super(ID_STATIC);
    }

    public CloseSession(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CloseSession(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CloseSession create(Object... values) {
        CloseSession m = new CloseSession();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CloseSession clone(IMCMessage msg) throws Exception {
        CloseSession m = new CloseSession();
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

    public long getSessid() {
        return getLong("sessid");
    }

    public CloseSession setSessid(long sessid) {
        values.put("sessid", sessid);
        return this;
    }

}
