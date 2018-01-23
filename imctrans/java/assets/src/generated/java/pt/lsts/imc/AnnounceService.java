package pt.lsts.imc;

public class AnnounceService extends IMCMessage {
    public static final int ID_STATIC = 152;

public static final short SRV_TYPE_EXTERNAL = 0x01;
public static final short SRV_TYPE_LOCAL = 0x02;

    public AnnounceService() {
        super(ID_STATIC);
    }

    public AnnounceService(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AnnounceService(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AnnounceService create(Object... values) {
        AnnounceService m = new AnnounceService();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AnnounceService clone(IMCMessage msg) throws Exception {
        AnnounceService m = new AnnounceService();
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

    public String getService() {
        return getString("service");
    }

    public AnnounceService setService(String service) {
        values.put("service", service);
        return this;
    }

    public short getServiceType() {
        return getShort("service_type");
    }

    public AnnounceService setServiceType(short service_type) {
        values.put("service_type", service_type);
        return this;
    }

}
