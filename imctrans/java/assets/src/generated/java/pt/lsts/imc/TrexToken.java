package pt.lsts.imc;

public class TrexToken extends IMCMessage {
    public static final int ID_STATIC = 657;


    public TrexToken() {
        super(ID_STATIC);
    }

    public TrexToken(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TrexToken(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrexToken create(Object... values) {
        TrexToken m = new TrexToken();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TrexToken clone(IMCMessage msg) throws Exception {
        TrexToken m = new TrexToken();
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

    public String getTimeline() {
        return getString("timeline");
    }

    public TrexToken setTimeline(String timeline) {
        values.put("timeline", timeline);
        return this;
    }

    public String getPredicate() {
        return getString("predicate");
    }

    public TrexToken setPredicate(String predicate) {
        values.put("predicate", predicate);
        return this;
    }

    public java.util.Vector<TrexAttribute> getAttributes() {
        return getMessageListOrNull("attributes", TrexAttribute.class);
    }

    public TrexToken setAttributes(java.util.Collection<TrexAttribute> attributes) {
        values.put("attributes", attributes);
        return this;
    }

}
