package pt.lsts.imc;

public class QueryLedBrightness extends IMCMessage {
    public static final int ID_STATIC = 313;


    public QueryLedBrightness() {
        super(ID_STATIC);
    }

    public QueryLedBrightness(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public QueryLedBrightness(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryLedBrightness create(Object... values) {
        QueryLedBrightness m = new QueryLedBrightness();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static QueryLedBrightness clone(IMCMessage msg) throws Exception {
        QueryLedBrightness m = new QueryLedBrightness();
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

    public QueryLedBrightness setName(String name) {
        values.put("name", name);
        return this;
    }

}
