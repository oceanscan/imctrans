package pt.lsts.imc;

public class QueryEntityActivationState extends IMCMessage {
    public static final int ID_STATIC = 15;


    public QueryEntityActivationState() {
        super(ID_STATIC);
    }

    public QueryEntityActivationState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public QueryEntityActivationState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryEntityActivationState create(Object... values) {
        QueryEntityActivationState m = new QueryEntityActivationState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static QueryEntityActivationState clone(IMCMessage msg) throws Exception {
        QueryEntityActivationState m = new QueryEntityActivationState();
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
