package pt.lsts.imc;

public class DevDataBinary extends IMCMessage {
    public static final int ID_STATIC = 274;


    public DevDataBinary() {
        super(ID_STATIC);
    }

    public DevDataBinary(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DevDataBinary(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DevDataBinary create(Object... values) {
        DevDataBinary m = new DevDataBinary();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DevDataBinary clone(IMCMessage msg) throws Exception {
        DevDataBinary m = new DevDataBinary();
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

    public byte[] getValue() {
        return getRawData("value");
    }

    public DevDataBinary setValue(byte[] value) {
        values.put("value", value);
        return this;
    }

}
