package pt.lsts.imc;

public class Heartbeat extends IMCMessage {
    public static final int ID_STATIC = 150;


    public Heartbeat() {
        super(ID_STATIC);
    }

    public Heartbeat(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Heartbeat(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Heartbeat create(Object... values) {
        Heartbeat m = new Heartbeat();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Heartbeat clone(IMCMessage msg) throws Exception {
        Heartbeat m = new Heartbeat();
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
