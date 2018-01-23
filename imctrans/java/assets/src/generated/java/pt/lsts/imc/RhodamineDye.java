package pt.lsts.imc;

public class RhodamineDye extends IMCMessage {
    public static final int ID_STATIC = 285;


    public RhodamineDye() {
        super(ID_STATIC);
    }

    public RhodamineDye(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RhodamineDye(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RhodamineDye create(Object... values) {
        RhodamineDye m = new RhodamineDye();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RhodamineDye clone(IMCMessage msg) throws Exception {
        RhodamineDye m = new RhodamineDye();
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

    public double getValue() {
        return getDouble("value");
    }

    public RhodamineDye setValue(double value) {
        values.put("value", value);
        return this;
    }

}
