package pt.lsts.imc;

public class PopEntityParameters extends IMCMessage {
    public static final int ID_STATIC = 812;


    public PopEntityParameters() {
        super(ID_STATIC);
    }

    public PopEntityParameters(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PopEntityParameters(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PopEntityParameters create(Object... values) {
        PopEntityParameters m = new PopEntityParameters();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PopEntityParameters clone(IMCMessage msg) throws Exception {
        PopEntityParameters m = new PopEntityParameters();
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

    public PopEntityParameters setName(String name) {
        values.put("name", name);
        return this;
    }

}
