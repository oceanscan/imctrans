package pt.lsts.imc;

public class QueryEntityParameters extends IMCMessage {
    public static final int ID_STATIC = 803;


    public QueryEntityParameters() {
        super(ID_STATIC);
    }

    public QueryEntityParameters(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public QueryEntityParameters(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryEntityParameters create(Object... values) {
        QueryEntityParameters m = new QueryEntityParameters();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static QueryEntityParameters clone(IMCMessage msg) throws Exception {
        QueryEntityParameters m = new QueryEntityParameters();
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

    public String getName() {
        return getString("name");
    }

    public QueryEntityParameters setName(String name) {
        values.put("name", name);
        return this;
    }

    public String getVisibility() {
        return getString("visibility");
    }

    public QueryEntityParameters setVisibility(String visibility) {
        values.put("visibility", visibility);
        return this;
    }

    public String getScope() {
        return getString("scope");
    }

    public QueryEntityParameters setScope(String scope) {
        values.put("scope", scope);
        return this;
    }

}
