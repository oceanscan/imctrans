package pt.lsts.imc;

public class GpsFix extends IMCMessage {
    public static final int ID_STATIC = 253;

public static final int GFV_VALID_DATE = 0x0001;
public static final int GFV_VALID_TIME = 0x0002;
public static final int GFV_VALID_POS = 0x0004;
public static final int GFV_VALID_COG = 0x0008;
public static final int GFV_VALID_SOG = 0x0010;
public static final int GFV_VALID_HACC = 0x0020;
public static final int GFV_VALID_VACC = 0x0040;
public static final int GFV_VALID_HDOP = 0x0080;
public static final int GFV_VALID_VDOP = 0x0100;

    public enum TYPE {
        STANDALONE(0x00),
        DIFFERENTIAL(0x01),
        DEAD_RECKONING(0x02),
        MANUAL_INPUT(0x03),
        SIMULATION(0x04);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public GpsFix() {
        super(ID_STATIC);
    }

    public GpsFix(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GpsFix(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GpsFix create(Object... values) {
        GpsFix m = new GpsFix();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GpsFix clone(IMCMessage msg) throws Exception {
        GpsFix m = new GpsFix();
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

    public int getValidity() {
        return getInteger("validity");
    }

    public GpsFix setValidity(int validity) {
        values.put("validity", validity);
        return this;
    }

    public TYPE getType() {
        try {
            TYPE o = TYPE.valueOf(getMessageType().getFieldPossibleValues("type").get(getLong("type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTypeStr() {
        return getString("type");
    }

    public short getTypeVal() {
        return getShort("type");
    }

    public GpsFix setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public GpsFix setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public GpsFix setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public int getUtcYear() {
        return getInteger("utc_year");
    }

    public GpsFix setUtcYear(int utc_year) {
        values.put("utc_year", utc_year);
        return this;
    }

    public short getUtcMonth() {
        return getShort("utc_month");
    }

    public GpsFix setUtcMonth(short utc_month) {
        values.put("utc_month", utc_month);
        return this;
    }

    public short getUtcDay() {
        return getShort("utc_day");
    }

    public GpsFix setUtcDay(short utc_day) {
        values.put("utc_day", utc_day);
        return this;
    }

    public double getUtcTime() {
        return getDouble("utc_time");
    }

    public GpsFix setUtcTime(double utc_time) {
        values.put("utc_time", utc_time);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public GpsFix setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public GpsFix setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getHeight() {
        return getDouble("height");
    }

    public GpsFix setHeight(double height) {
        values.put("height", height);
        return this;
    }

    public short getSatellites() {
        return getShort("satellites");
    }

    public GpsFix setSatellites(short satellites) {
        values.put("satellites", satellites);
        return this;
    }

    public double getCog() {
        return getDouble("cog");
    }

    public GpsFix setCog(double cog) {
        values.put("cog", cog);
        return this;
    }

    public double getSog() {
        return getDouble("sog");
    }

    public GpsFix setSog(double sog) {
        values.put("sog", sog);
        return this;
    }

    public double getHdop() {
        return getDouble("hdop");
    }

    public GpsFix setHdop(double hdop) {
        values.put("hdop", hdop);
        return this;
    }

    public double getVdop() {
        return getDouble("vdop");
    }

    public GpsFix setVdop(double vdop) {
        values.put("vdop", vdop);
        return this;
    }

    public double getHacc() {
        return getDouble("hacc");
    }

    public GpsFix setHacc(double hacc) {
        values.put("hacc", hacc);
        return this;
    }

    public double getVacc() {
        return getDouble("vacc");
    }

    public GpsFix setVacc(double vacc) {
        values.put("vacc", vacc);
        return this;
    }

}
