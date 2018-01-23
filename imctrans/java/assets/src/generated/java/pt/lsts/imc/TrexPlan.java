package pt.lsts.imc;

public class TrexPlan extends IMCMessage {
    public static final int ID_STATIC = 658;


    public TrexPlan() {
        super(ID_STATIC);
    }

    public TrexPlan(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TrexPlan(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrexPlan create(Object... values) {
        TrexPlan m = new TrexPlan();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TrexPlan clone(IMCMessage msg) throws Exception {
        TrexPlan m = new TrexPlan();
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

    public String getReactor() {
        return getString("reactor");
    }

    public TrexPlan setReactor(String reactor) {
        values.put("reactor", reactor);
        return this;
    }

    public java.util.Vector<TrexToken> getTokens() {
        return getMessageListOrNull("tokens", TrexToken.class);
    }

    public TrexPlan setTokens(java.util.Collection<TrexToken> tokens) {
        values.put("tokens", tokens);
        return this;
    }

}
