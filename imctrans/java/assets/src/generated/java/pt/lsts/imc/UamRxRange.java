package pt.lsts.imc;

public class UamRxRange extends IMCMessage {
    public static final int ID_STATIC = 817;


    public UamRxRange() {
        super(ID_STATIC);
    }

    public UamRxRange(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UamRxRange(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UamRxRange create(Object... values) {
        UamRxRange m = new UamRxRange();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UamRxRange clone(IMCMessage msg) throws Exception {
        UamRxRange m = new UamRxRange();
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

    public int getSeq() {
        return getInteger("seq");
    }

    public UamRxRange setSeq(int seq) {
        values.put("seq", seq);
        return this;
    }

    public String getSys() {
        return getString("sys");
    }

    public UamRxRange setSys(String sys) {
        values.put("sys", sys);
        return this;
    }

    public double getValue() {
        return getDouble("value");
    }

    public UamRxRange setValue(double value) {
        values.put("value", value);
        return this;
    }

}
