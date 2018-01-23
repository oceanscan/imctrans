package pt.lsts.imc;

public class ReportedState extends IMCMessage {
    public static final int ID_STATIC = 600;


    public enum S_TYPE {
        WI_FI(0),
        TRACKER(1),
        SMS(2),
        ACOUSTIC_MODEM(3),
        UNKNOWN(254);

        protected long value;

        public long value() {
            return value;
        }

        S_TYPE(long value) {
            this.value = value;
        }
    }

    public ReportedState() {
        super(ID_STATIC);
    }

    public ReportedState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ReportedState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ReportedState create(Object... values) {
        ReportedState m = new ReportedState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ReportedState clone(IMCMessage msg) throws Exception {
        ReportedState m = new ReportedState();
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

    public double getLat() {
        return getDouble("lat");
    }

    public ReportedState setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public ReportedState setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getDepth() {
        return getDouble("depth");
    }

    public ReportedState setDepth(double depth) {
        values.put("depth", depth);
        return this;
    }

    public double getRoll() {
        return getDouble("roll");
    }

    public ReportedState setRoll(double roll) {
        values.put("roll", roll);
        return this;
    }

    public double getPitch() {
        return getDouble("pitch");
    }

    public ReportedState setPitch(double pitch) {
        values.put("pitch", pitch);
        return this;
    }

    public double getYaw() {
        return getDouble("yaw");
    }

    public ReportedState setYaw(double yaw) {
        values.put("yaw", yaw);
        return this;
    }

    public double getRcpTime() {
        return getDouble("rcp_time");
    }

    public ReportedState setRcpTime(double rcp_time) {
        values.put("rcp_time", rcp_time);
        return this;
    }

    public String getSid() {
        return getString("sid");
    }

    public ReportedState setSid(String sid) {
        values.put("sid", sid);
        return this;
    }

    public S_TYPE getSType() {
        try {
            S_TYPE o = S_TYPE.valueOf(getMessageType().getFieldPossibleValues("s_type").get(getLong("s_type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSTypeStr() {
        return getString("s_type");
    }

    public short getSTypeVal() {
        return getShort("s_type");
    }

    public ReportedState setSTypeStr(String s_type) {
        setValue("s_type", s_type);
        return this;
    }

    public ReportedState setSTypeVal(S_TYPE s_type) {
        setValue("s_type", s_type);
        return this;
    }

    public ReportedState setSType(S_TYPE s_type) {
        values.put("s_type", s_type.value());
        return this;
    }

}
