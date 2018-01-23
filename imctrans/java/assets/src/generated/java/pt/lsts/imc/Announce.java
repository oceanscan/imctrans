package pt.lsts.imc;

public class Announce extends IMCMessage {
    public static final int ID_STATIC = 151;


    public enum SYS_TYPE {
        CCU(0),
        HUMANSENSOR(1),
        UUV(2),
        USV(3),
        UAV(4),
        UGV(5),
        STATICSENSOR(6),
        MOBILESENSOR(7),
        WSN(8);

        protected long value;

        public long value() {
            return value;
        }

        SYS_TYPE(long value) {
            this.value = value;
        }
    }

    public Announce() {
        super(ID_STATIC);
    }

    public Announce(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Announce(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Announce create(Object... values) {
        Announce m = new Announce();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Announce clone(IMCMessage msg) throws Exception {
        Announce m = new Announce();
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

    public String getSysName() {
        return getString("sys_name");
    }

    public Announce setSysName(String sys_name) {
        values.put("sys_name", sys_name);
        return this;
    }

    public SYS_TYPE getSysType() {
        try {
            SYS_TYPE o = SYS_TYPE.valueOf(getMessageType().getFieldPossibleValues("sys_type").get(getLong("sys_type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSysTypeStr() {
        return getString("sys_type");
    }

    public short getSysTypeVal() {
        return getShort("sys_type");
    }

    public Announce setSysTypeStr(String sys_type) {
        setValue("sys_type", sys_type);
        return this;
    }

    public Announce setSysTypeVal(SYS_TYPE sys_type) {
        setValue("sys_type", sys_type);
        return this;
    }

    public Announce setSysType(SYS_TYPE sys_type) {
        values.put("sys_type", sys_type.value());
        return this;
    }

    public int getOwner() {
        return getInteger("owner");
    }

    public Announce setOwner(int owner) {
        values.put("owner", owner);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Announce setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Announce setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getHeight() {
        return getDouble("height");
    }

    public Announce setHeight(double height) {
        values.put("height", height);
        return this;
    }

    public String getServices() {
        return getString("services");
    }

    public Announce setServices(String services) {
        values.put("services", services);
        return this;
    }

}
