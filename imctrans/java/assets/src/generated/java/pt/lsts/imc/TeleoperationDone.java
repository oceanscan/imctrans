package pt.lsts.imc;

public class TeleoperationDone extends IMCMessage {
    public static final int ID_STATIC = 460;


    public TeleoperationDone() {
        super(ID_STATIC);
    }

    public TeleoperationDone(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TeleoperationDone(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TeleoperationDone create(Object... values) {
        TeleoperationDone m = new TeleoperationDone();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TeleoperationDone clone(IMCMessage msg) throws Exception {
        TeleoperationDone m = new TeleoperationDone();
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
