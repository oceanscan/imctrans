package pt.lsts.imc;

public class QueryEntityState extends IMCMessage {
    public static final int ID_STATIC = 2;


    public QueryEntityState() {
        super(ID_STATIC);
    }

    public QueryEntityState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public QueryEntityState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryEntityState create(Object... values) {
        QueryEntityState m = new QueryEntityState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static QueryEntityState clone(IMCMessage msg) throws Exception {
        QueryEntityState m = new QueryEntityState();
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
