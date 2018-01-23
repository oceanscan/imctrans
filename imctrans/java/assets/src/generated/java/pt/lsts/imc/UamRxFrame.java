package pt.lsts.imc;

public class UamRxFrame extends IMCMessage {
    public static final int ID_STATIC = 815;

public static final short URF_PROMISCUOUS = 0x01;
public static final short URF_DELAYED = 0x02;

    public UamRxFrame() {
        super(ID_STATIC);
    }

    public UamRxFrame(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UamRxFrame(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UamRxFrame create(Object... values) {
        UamRxFrame m = new UamRxFrame();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UamRxFrame clone(IMCMessage msg) throws Exception {
        UamRxFrame m = new UamRxFrame();
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

    public String getSysSrc() {
        return getString("sys_src");
    }

    public UamRxFrame setSysSrc(String sys_src) {
        values.put("sys_src", sys_src);
        return this;
    }

    public String getSysDst() {
        return getString("sys_dst");
    }

    public UamRxFrame setSysDst(String sys_dst) {
        values.put("sys_dst", sys_dst);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public UamRxFrame setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public UamRxFrame setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
