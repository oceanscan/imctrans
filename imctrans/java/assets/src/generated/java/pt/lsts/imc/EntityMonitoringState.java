package pt.lsts.imc;

public class EntityMonitoringState extends IMCMessage {
    public static final int ID_STATIC = 503;


    public EntityMonitoringState() {
        super(ID_STATIC);
    }

    public EntityMonitoringState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EntityMonitoringState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityMonitoringState create(Object... values) {
        EntityMonitoringState m = new EntityMonitoringState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EntityMonitoringState clone(IMCMessage msg) throws Exception {
        EntityMonitoringState m = new EntityMonitoringState();
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

    public short getMcount() {
        return getShort("mcount");
    }

    public EntityMonitoringState setMcount(short mcount) {
        values.put("mcount", mcount);
        return this;
    }

    public String getMnames() {
        return getString("mnames");
    }

    public EntityMonitoringState setMnames(String mnames) {
        values.put("mnames", mnames);
        return this;
    }

    public short getEcount() {
        return getShort("ecount");
    }

    public EntityMonitoringState setEcount(short ecount) {
        values.put("ecount", ecount);
        return this;
    }

    public String getEnames() {
        return getString("enames");
    }

    public EntityMonitoringState setEnames(String enames) {
        values.put("enames", enames);
        return this;
    }

    public short getCcount() {
        return getShort("ccount");
    }

    public EntityMonitoringState setCcount(short ccount) {
        values.put("ccount", ccount);
        return this;
    }

    public String getCnames() {
        return getString("cnames");
    }

    public EntityMonitoringState setCnames(String cnames) {
        values.put("cnames", cnames);
        return this;
    }

    public String getLastError() {
        return getString("last_error");
    }

    public EntityMonitoringState setLastError(String last_error) {
        values.put("last_error", last_error);
        return this;
    }

    public double getLastErrorTime() {
        return getDouble("last_error_time");
    }

    public EntityMonitoringState setLastErrorTime(double last_error_time) {
        values.put("last_error_time", last_error_time);
        return this;
    }

}
