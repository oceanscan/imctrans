package pt.lsts.imc;

public class GpioStateSet extends IMCMessage {
    public static final int ID_STATIC = 2002;


    public GpioStateSet() {
        super(ID_STATIC);
    }

    public GpioStateSet(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GpioStateSet(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GpioStateSet create(Object... values) {
        GpioStateSet m = new GpioStateSet();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GpioStateSet clone(IMCMessage msg) throws Exception {
        GpioStateSet m = new GpioStateSet();
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

    public GpioStateSet setName(String name) {
        values.put("name", name);
        return this;
    }

    public short getValue() {
        return getShort("value");
    }

    public GpioStateSet setValue(short value) {
        values.put("value", value);
        return this;
    }

}
