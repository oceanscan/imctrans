package pt.lsts.imc;

public class Aborted extends IMCMessage {
    public static final int ID_STATIC = 889;


    public Aborted() {
        super(ID_STATIC);
    }

    public Aborted(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Aborted(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Aborted create(Object... values) {
        Aborted m = new Aborted();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Aborted clone(IMCMessage msg) throws Exception {
        Aborted m = new Aborted();
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
