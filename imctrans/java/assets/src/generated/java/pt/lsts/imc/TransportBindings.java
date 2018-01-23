package pt.lsts.imc;

public class TransportBindings extends IMCMessage {
    public static final int ID_STATIC = 8;


    public TransportBindings() {
        super(ID_STATIC);
    }

    public TransportBindings(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TransportBindings(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TransportBindings create(Object... values) {
        TransportBindings m = new TransportBindings();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TransportBindings clone(IMCMessage msg) throws Exception {
        TransportBindings m = new TransportBindings();
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

    public String getConsumer() {
        return getString("consumer");
    }

    public TransportBindings setConsumer(String consumer) {
        values.put("consumer", consumer);
        return this;
    }

    public int getMessageId() {
        return getInteger("message_id");
    }

    public TransportBindings setMessageId(int message_id) {
        values.put("message_id", message_id);
        return this;
    }

}
