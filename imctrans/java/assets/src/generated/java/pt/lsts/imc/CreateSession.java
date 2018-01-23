package pt.lsts.imc;

public class CreateSession extends IMCMessage {
    public static final int ID_STATIC = 806;


    public CreateSession() {
        super(ID_STATIC);
    }

    public CreateSession(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CreateSession(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CreateSession create(Object... values) {
        CreateSession m = new CreateSession();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CreateSession clone(IMCMessage msg) throws Exception {
        CreateSession m = new CreateSession();
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

    public long getTimeout() {
        return getLong("timeout");
    }

    public CreateSession setTimeout(long timeout) {
        values.put("timeout", timeout);
        return this;
    }

}
