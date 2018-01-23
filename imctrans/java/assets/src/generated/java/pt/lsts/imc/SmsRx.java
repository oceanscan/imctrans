package pt.lsts.imc;

public class SmsRx extends IMCMessage {
    public static final int ID_STATIC = 158;


    public SmsRx() {
        super(ID_STATIC);
    }

    public SmsRx(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SmsRx(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SmsRx create(Object... values) {
        SmsRx m = new SmsRx();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SmsRx clone(IMCMessage msg) throws Exception {
        SmsRx m = new SmsRx();
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

    public String getSource() {
        return getString("source");
    }

    public SmsRx setSource(String source) {
        values.put("source", source);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public SmsRx setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
