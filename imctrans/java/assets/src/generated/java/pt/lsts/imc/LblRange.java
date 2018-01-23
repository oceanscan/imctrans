package pt.lsts.imc;

public class LblRange extends IMCMessage {
    public static final int ID_STATIC = 200;


    public LblRange() {
        super(ID_STATIC);
    }

    public LblRange(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LblRange(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LblRange create(Object... values) {
        LblRange m = new LblRange();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LblRange clone(IMCMessage msg) throws Exception {
        LblRange m = new LblRange();
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

    public short getId() {
        return getShort("id");
    }

    public LblRange setId(short id) {
        values.put("id", id);
        return this;
    }

    public double getRange() {
        return getDouble("range");
    }

    public LblRange setRange(double range) {
        values.put("range", range);
        return this;
    }

}
