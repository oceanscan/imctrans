package pt.lsts.imc;

public class Rpm extends IMCMessage {
    public static final int ID_STATIC = 250;


    public Rpm() {
        super(ID_STATIC);
    }

    public Rpm(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Rpm(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Rpm create(Object... values) {
        Rpm m = new Rpm();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Rpm clone(IMCMessage msg) throws Exception {
        Rpm m = new Rpm();
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

    public short getValue() {
        return getShort("value");
    }

    public Rpm setValue(short value) {
        values.put("value", value);
        return this;
    }

}
