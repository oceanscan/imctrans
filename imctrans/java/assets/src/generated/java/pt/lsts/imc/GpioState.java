package pt.lsts.imc;

public class GpioState extends IMCMessage {
    public static final int ID_STATIC = 2000;


    public GpioState() {
        super(ID_STATIC);
    }

    public GpioState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GpioState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GpioState create(Object... values) {
        GpioState m = new GpioState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GpioState clone(IMCMessage msg) throws Exception {
        GpioState m = new GpioState();
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

    public GpioState setName(String name) {
        values.put("name", name);
        return this;
    }

    public short getValue() {
        return getShort("value");
    }

    public GpioState setValue(short value) {
        values.put("value", value);
        return this;
    }

}
