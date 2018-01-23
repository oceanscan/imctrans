package pt.lsts.imc;

public class LinkLatency extends IMCMessage {
    public static final int ID_STATIC = 182;


    public LinkLatency() {
        super(ID_STATIC);
    }

    public LinkLatency(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LinkLatency(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LinkLatency create(Object... values) {
        LinkLatency m = new LinkLatency();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LinkLatency clone(IMCMessage msg) throws Exception {
        LinkLatency m = new LinkLatency();
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

    public LinkLatency setValue(double value) {
        values.put("value", value);
        return this;
    }

    public int getSysSrc() {
        return getInteger("sys_src");
    }

    public LinkLatency setSysSrc(int sys_src) {
        values.put("sys_src", sys_src);
        return this;
    }

}
