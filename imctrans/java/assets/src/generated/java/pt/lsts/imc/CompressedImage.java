package pt.lsts.imc;

public class CompressedImage extends IMCMessage {
    public static final int ID_STATIC = 702;


    public CompressedImage() {
        super(ID_STATIC);
    }

    public CompressedImage(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CompressedImage(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CompressedImage create(Object... values) {
        CompressedImage m = new CompressedImage();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CompressedImage clone(IMCMessage msg) throws Exception {
        CompressedImage m = new CompressedImage();
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

    public short getFrameid() {
        return getShort("frameid");
    }

    public CompressedImage setFrameid(short frameid) {
        values.put("frameid", frameid);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public CompressedImage setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
