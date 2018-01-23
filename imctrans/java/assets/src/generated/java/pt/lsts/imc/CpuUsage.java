package pt.lsts.imc;

public class CpuUsage extends IMCMessage {
    public static final int ID_STATIC = 7;


    public CpuUsage() {
        super(ID_STATIC);
    }

    public CpuUsage(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CpuUsage(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CpuUsage create(Object... values) {
        CpuUsage m = new CpuUsage();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CpuUsage clone(IMCMessage msg) throws Exception {
        CpuUsage m = new CpuUsage();
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

    public short getValue() {
        return getShort("value");
    }

    public CpuUsage setValue(short value) {
        values.put("value", value);
        return this;
    }

}
