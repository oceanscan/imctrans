package pt.lsts.imc;

public class Phycocyanin extends IMCMessage {
    public static final int ID_STATIC = 291;


    public Phycocyanin() {
        super(ID_STATIC);
    }

    public Phycocyanin(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Phycocyanin(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Phycocyanin create(Object... values) {
        Phycocyanin m = new Phycocyanin();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Phycocyanin clone(IMCMessage msg) throws Exception {
        Phycocyanin m = new Phycocyanin();
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

    public Phycocyanin setValue(double value) {
        values.put("value", value);
        return this;
    }

}
