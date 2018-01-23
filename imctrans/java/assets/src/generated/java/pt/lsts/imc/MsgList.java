package pt.lsts.imc;

public class MsgList extends IMCMessage {
    public static final int ID_STATIC = 20;


    public MsgList() {
        super(ID_STATIC);
    }

    public MsgList(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public MsgList(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MsgList create(Object... values) {
        MsgList m = new MsgList();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static MsgList clone(IMCMessage msg) throws Exception {
        MsgList m = new MsgList();
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

    public java.util.Vector<IMCMessage> getMsgs() {
        return getMessageListOrNull("msgs", IMCMessage.class);
    }

    public MsgList setMsgs(java.util.Collection<IMCMessage> msgs) {
        values.put("msgs", msgs);
        return this;
    }

}
