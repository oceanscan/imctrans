package pt.lsts.imc;

public class SaveEntityParameters extends IMCMessage {
    public static final int ID_STATIC = 805;


    public SaveEntityParameters() {
        super(ID_STATIC);
    }

    public SaveEntityParameters(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SaveEntityParameters(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SaveEntityParameters create(Object... values) {
        SaveEntityParameters m = new SaveEntityParameters();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SaveEntityParameters clone(IMCMessage msg) throws Exception {
        SaveEntityParameters m = new SaveEntityParameters();
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

    public SaveEntityParameters setName(String name) {
        values.put("name", name);
        return this;
    }

}
