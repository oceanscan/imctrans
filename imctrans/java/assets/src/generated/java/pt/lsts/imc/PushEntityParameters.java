package pt.lsts.imc;

public class PushEntityParameters extends IMCMessage {
    public static final int ID_STATIC = 811;


    public PushEntityParameters() {
        super(ID_STATIC);
    }

    public PushEntityParameters(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PushEntityParameters(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PushEntityParameters create(Object... values) {
        PushEntityParameters m = new PushEntityParameters();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PushEntityParameters clone(IMCMessage msg) throws Exception {
        PushEntityParameters m = new PushEntityParameters();
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

    public PushEntityParameters setName(String name) {
        values.put("name", name);
        return this;
    }

}
