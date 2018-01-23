package pt.lsts.imc;

public class StorageUsage extends IMCMessage {
    public static final int ID_STATIC = 100;


    public StorageUsage() {
        super(ID_STATIC);
    }

    public StorageUsage(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public StorageUsage(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StorageUsage create(Object... values) {
        StorageUsage m = new StorageUsage();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static StorageUsage clone(IMCMessage msg) throws Exception {
        StorageUsage m = new StorageUsage();
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

    public long getAvailable() {
        return getLong("available");
    }

    public StorageUsage setAvailable(long available) {
        values.put("available", available);
        return this;
    }

    public short getValue() {
        return getShort("value");
    }

    public StorageUsage setValue(short value) {
        values.put("value", value);
        return this;
    }

}
