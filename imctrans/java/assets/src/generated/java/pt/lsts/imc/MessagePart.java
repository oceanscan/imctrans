package pt.lsts.imc;

public class MessagePart extends IMCMessage {
    public static final int ID_STATIC = 877;


    public MessagePart() {
        super(ID_STATIC);
    }

    public MessagePart(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public MessagePart(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MessagePart create(Object... values) {
        MessagePart m = new MessagePart();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static MessagePart clone(IMCMessage msg) throws Exception {
        MessagePart m = new MessagePart();
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

    public short getUid() {
        return getShort("uid");
    }

    public MessagePart setUid(short uid) {
        values.put("uid", uid);
        return this;
    }

    public short getFragNumber() {
        return getShort("frag_number");
    }

    public MessagePart setFragNumber(short frag_number) {
        values.put("frag_number", frag_number);
        return this;
    }

    public short getNumFrags() {
        return getShort("num_frags");
    }

    public MessagePart setNumFrags(short num_frags) {
        values.put("num_frags", num_frags);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public MessagePart setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
