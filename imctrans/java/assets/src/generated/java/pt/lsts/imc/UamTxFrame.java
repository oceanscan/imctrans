package pt.lsts.imc;

public class UamTxFrame extends IMCMessage {
    public static final int ID_STATIC = 814;

public static final short UTF_ACK = 0x01;
public static final short UTF_DELAYED = 0x02;

    public UamTxFrame() {
        super(ID_STATIC);
    }

    public UamTxFrame(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UamTxFrame(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UamTxFrame create(Object... values) {
        UamTxFrame m = new UamTxFrame();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UamTxFrame clone(IMCMessage msg) throws Exception {
        UamTxFrame m = new UamTxFrame();
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

    public int getSeq() {
        return getInteger("seq");
    }

    public UamTxFrame setSeq(int seq) {
        values.put("seq", seq);
        return this;
    }

    public String getSysDst() {
        return getString("sys_dst");
    }

    public UamTxFrame setSysDst(String sys_dst) {
        values.put("sys_dst", sys_dst);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public UamTxFrame setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public UamTxFrame setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
