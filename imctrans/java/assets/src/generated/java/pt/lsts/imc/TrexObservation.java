package pt.lsts.imc;

public class TrexObservation extends IMCMessage {
    public static final int ID_STATIC = 651;


    public TrexObservation() {
        super(ID_STATIC);
    }

    public TrexObservation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TrexObservation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrexObservation create(Object... values) {
        TrexObservation m = new TrexObservation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TrexObservation clone(IMCMessage msg) throws Exception {
        TrexObservation m = new TrexObservation();
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

    public TrexObservation setTimeline(String timeline) {
        values.put("timeline", timeline);
        return this;
    }

    public String getPredicate() {
        return getString("predicate");
    }

    public TrexObservation setPredicate(String predicate) {
        values.put("predicate", predicate);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getAttributes() {
        return getTupleList("attributes");
    }

    public TrexObservation setAttributes(java.util.LinkedHashMap<String, ?> attributes) {
        return setAttributes(attributes);
    }

    public TrexObservation setAttributes(String attributes) {
        values.put("attributes", attributes);
        return this;
    }

}
