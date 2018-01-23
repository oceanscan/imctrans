package pt.lsts.imc;

public class NeptusBlob extends IMCMessage {
    public static final int ID_STATIC = 888;


    public NeptusBlob() {
        super(ID_STATIC);
    }

    public NeptusBlob(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public NeptusBlob(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NeptusBlob create(Object... values) {
        NeptusBlob m = new NeptusBlob();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static NeptusBlob clone(IMCMessage msg) throws Exception {
        NeptusBlob m = new NeptusBlob();
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

    public String getContentType() {
        return getString("content_type");
    }

    public NeptusBlob setContentType(String content_type) {
        values.put("content_type", content_type);
        return this;
    }

    public byte[] getContent() {
        return getRawData("content");
    }

    public NeptusBlob setContent(byte[] content) {
        values.put("content", content);
        return this;
    }

}
