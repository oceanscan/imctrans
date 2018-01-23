package pt.lsts.imc;

public class SessionSubscription extends IMCMessage {
    public static final int ID_STATIC = 808;


    public SessionSubscription() {
        super(ID_STATIC);
    }

    public SessionSubscription(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SessionSubscription(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionSubscription create(Object... values) {
        SessionSubscription m = new SessionSubscription();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SessionSubscription clone(IMCMessage msg) throws Exception {
        SessionSubscription m = new SessionSubscription();
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

    public SessionSubscription setSessid(long sessid) {
        values.put("sessid", sessid);
        return this;
    }

    public String getMessages() {
        return getString("messages");
    }

    public SessionSubscription setMessages(String messages) {
        values.put("messages", messages);
        return this;
    }

}
