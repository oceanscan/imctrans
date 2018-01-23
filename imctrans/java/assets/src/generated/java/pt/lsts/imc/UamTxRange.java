package pt.lsts.imc;

public class UamTxRange extends IMCMessage {
    public static final int ID_STATIC = 818;


    public UamTxRange() {
        super(ID_STATIC);
    }

    public UamTxRange(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UamTxRange(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UamTxRange create(Object... values) {
        UamTxRange m = new UamTxRange();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UamTxRange clone(IMCMessage msg) throws Exception {
        UamTxRange m = new UamTxRange();
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

    public UamTxRange setSeq(int seq) {
        values.put("seq", seq);
        return this;
    }

    public String getSysDst() {
        return getString("sys_dst");
    }

    public UamTxRange setSysDst(String sys_dst) {
        values.put("sys_dst", sys_dst);
        return this;
    }

    public double getTimeout() {
        return getDouble("timeout");
    }

    public UamTxRange setTimeout(double timeout) {
        values.put("timeout", timeout);
        return this;
    }

}
