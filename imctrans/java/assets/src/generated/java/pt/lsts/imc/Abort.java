package pt.lsts.imc;

public class Abort extends IMCMessage {
    public static final int ID_STATIC = 550;


    public Abort() {
        super(ID_STATIC);
    }

    public Abort(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Abort(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Abort create(Object... values) {
        Abort m = new Abort();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Abort clone(IMCMessage msg) throws Exception {
        Abort m = new Abort();
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
