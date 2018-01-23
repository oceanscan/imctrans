package pt.lsts.imc;

public class QueryEntityInfo extends IMCMessage {
    public static final int ID_STATIC = 4;


    public QueryEntityInfo() {
        super(ID_STATIC);
    }

    public QueryEntityInfo(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public QueryEntityInfo(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryEntityInfo create(Object... values) {
        QueryEntityInfo m = new QueryEntityInfo();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static QueryEntityInfo clone(IMCMessage msg) throws Exception {
        QueryEntityInfo m = new QueryEntityInfo();
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

    public short getId() {
        return getShort("id");
    }

    public QueryEntityInfo setId(short id) {
        values.put("id", id);
        return this;
    }

}
