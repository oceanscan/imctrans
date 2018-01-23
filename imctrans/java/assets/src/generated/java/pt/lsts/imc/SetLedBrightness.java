package pt.lsts.imc;

public class SetLedBrightness extends IMCMessage {
    public static final int ID_STATIC = 314;


    public SetLedBrightness() {
        super(ID_STATIC);
    }

    public SetLedBrightness(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SetLedBrightness(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SetLedBrightness create(Object... values) {
        SetLedBrightness m = new SetLedBrightness();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SetLedBrightness clone(IMCMessage msg) throws Exception {
        SetLedBrightness m = new SetLedBrightness();
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

    public SetLedBrightness setName(String name) {
        values.put("name", name);
        return this;
    }

    public short getValue() {
        return getShort("value");
    }

    public SetLedBrightness setValue(short value) {
        values.put("value", value);
        return this;
    }

}
