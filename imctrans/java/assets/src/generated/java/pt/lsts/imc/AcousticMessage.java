package pt.lsts.imc;

public class AcousticMessage extends IMCMessage {
    public static final int ID_STATIC = 206;


    public AcousticMessage() {
        super(ID_STATIC);
    }

    public AcousticMessage(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AcousticMessage(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AcousticMessage create(Object... values) {
        AcousticMessage m = new AcousticMessage();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AcousticMessage clone(IMCMessage msg) throws Exception {
        AcousticMessage m = new AcousticMessage();
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

    public IMCMessage getMessage() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getMessage(Class<T> clazz) throws Exception {
        return getMessage(clazz, "message");
    }

    public AcousticMessage setMessage(IMCMessage message) {
        values.put("message", message);
        return this;
    }

}
