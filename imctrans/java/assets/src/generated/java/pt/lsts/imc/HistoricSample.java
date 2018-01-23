package pt.lsts.imc;

public class HistoricSample extends RemoteData {
    public static final int ID_STATIC = 186;


    public HistoricSample() {
        super(ID_STATIC);
    }

    public HistoricSample(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public HistoricSample(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HistoricSample create(Object... values) {
        HistoricSample m = new HistoricSample();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static HistoricSample clone(IMCMessage msg) throws Exception {
        HistoricSample m = new HistoricSample();
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

    public int getSysId() {
        return getInteger("sys_id");
    }

    public HistoricSample setSysId(int sys_id) {
        values.put("sys_id", sys_id);
        return this;
    }

    public byte getPriority() {
        return getByte("priority");
    }

    public HistoricSample setPriority(byte priority) {
        values.put("priority", priority);
        return this;
    }

    public short getX() {
        return getShort("x");
    }

    public HistoricSample setX(short x) {
        values.put("x", x);
        return this;
    }

    public short getY() {
        return getShort("y");
    }

    public HistoricSample setY(short y) {
        values.put("y", y);
        return this;
    }

    public short getZ() {
        return getShort("z");
    }

    public HistoricSample setZ(short z) {
        values.put("z", z);
        return this;
    }

    public short getT() {
        return getShort("t");
    }

    public HistoricSample setT(short t) {
        values.put("t", t);
        return this;
    }

    public IMCMessage getSample() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getSample(Class<T> clazz) throws Exception {
        return getMessage(clazz, "sample");
    }

    public HistoricSample setSample(IMCMessage sample) {
        values.put("sample", sample);
        return this;
    }

}
