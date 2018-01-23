package pt.lsts.imc;

public class ParametersXml extends IMCMessage {
    public static final int ID_STATIC = 893;


    public ParametersXml() {
        super(ID_STATIC);
    }

    public ParametersXml(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ParametersXml(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ParametersXml create(Object... values) {
        ParametersXml m = new ParametersXml();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ParametersXml clone(IMCMessage msg) throws Exception {
        ParametersXml m = new ParametersXml();
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

    public String getLocale() {
        return getString("locale");
    }

    public ParametersXml setLocale(String locale) {
        values.put("locale", locale);
        return this;
    }

    public byte[] getConfig() {
        return getRawData("config");
    }

    public ParametersXml setConfig(byte[] config) {
        values.put("config", config);
        return this;
    }

}
