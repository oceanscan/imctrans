package pt.lsts.imc;

public class DevDataText extends IMCMessage {
    public static final int ID_STATIC = 273;


    public DevDataText() {
        super(ID_STATIC);
    }

    public DevDataText(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DevDataText(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DevDataText create(Object... values) {
        DevDataText m = new DevDataText();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DevDataText clone(IMCMessage msg) throws Exception {
        DevDataText m = new DevDataText();
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

    public String getValue() {
        return getString("value");
    }

    public DevDataText setValue(String value) {
        values.put("value", value);
        return this;
    }

}
