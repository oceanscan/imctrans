package pt.lsts.imc;

public class LedBrightness extends IMCMessage {
    public static final int ID_STATIC = 312;


    public LedBrightness() {
        super(ID_STATIC);
    }

    public LedBrightness(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LedBrightness(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LedBrightness create(Object... values) {
        LedBrightness m = new LedBrightness();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LedBrightness clone(IMCMessage msg) throws Exception {
        LedBrightness m = new LedBrightness();
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

    public LedBrightness setName(String name) {
        values.put("name", name);
        return this;
    }

    public short getValue() {
        return getShort("value");
    }

    public LedBrightness setValue(short value) {
        values.put("value", value);
        return this;
    }

}
