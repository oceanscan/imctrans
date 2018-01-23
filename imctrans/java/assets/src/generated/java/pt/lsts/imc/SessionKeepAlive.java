package pt.lsts.imc;

public class SessionKeepAlive extends IMCMessage {
    public static final int ID_STATIC = 809;


    public SessionKeepAlive() {
        super(ID_STATIC);
    }

    public SessionKeepAlive(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SessionKeepAlive(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionKeepAlive create(Object... values) {
        SessionKeepAlive m = new SessionKeepAlive();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SessionKeepAlive clone(IMCMessage msg) throws Exception {
        SessionKeepAlive m = new SessionKeepAlive();
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

    public SessionKeepAlive setSessid(long sessid) {
        values.put("sessid", sessid);
        return this;
    }

}
