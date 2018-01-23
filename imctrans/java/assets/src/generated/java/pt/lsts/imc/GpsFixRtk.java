package pt.lsts.imc;

public class GpsFixRtk extends IMCMessage {
    public static final int ID_STATIC = 293;

public static final int RFV_VALID_TIME = 0x0001;
public static final int RFV_VALID_BASE = 0x0002;
public static final int RFV_VALID_POS = 0x0004;
public static final int RFV_VALID_VEL = 0x0008;

    public enum TYPE {
        NONE(0x00),
        OBS(0x01),
        FLOAT(0x02),
        FIXED(0x03);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public GpsFixRtk() {
        super(ID_STATIC);
    }

    public GpsFixRtk(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GpsFixRtk(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GpsFixRtk create(Object... values) {
        GpsFixRtk m = new GpsFixRtk();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GpsFixRtk clone(IMCMessage msg) throws Exception {
        GpsFixRtk m = new GpsFixRtk();
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

    public GpsFixRtk setValidity(int validity) {
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

    public GpsFixRtk setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public GpsFixRtk setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public GpsFixRtk setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public long getTow() {
        return getLong("tow");
    }

    public GpsFixRtk setTow(long tow) {
        values.put("tow", tow);
        return this;
    }

    public double getBaseLat() {
        return getDouble("base_lat");
    }

    public GpsFixRtk setBaseLat(double base_lat) {
        values.put("base_lat", base_lat);
        return this;
    }

    public double getBaseLon() {
        return getDouble("base_lon");
    }

    public GpsFixRtk setBaseLon(double base_lon) {
        values.put("base_lon", base_lon);
        return this;
    }

    public double getBaseHeight() {
        return getDouble("base_height");
    }

    public GpsFixRtk setBaseHeight(double base_height) {
        values.put("base_height", base_height);
        return this;
    }

    public double getN() {
        return getDouble("n");
    }

    public GpsFixRtk setN(double n) {
        values.put("n", n);
        return this;
    }

    public double getE() {
        return getDouble("e");
    }

    public GpsFixRtk setE(double e) {
        values.put("e", e);
        return this;
    }

    public double getD() {
        return getDouble("d");
    }

    public GpsFixRtk setD(double d) {
        values.put("d", d);
        return this;
    }

    public double getVN() {
        return getDouble("v_n");
    }

    public GpsFixRtk setVN(double v_n) {
        values.put("v_n", v_n);
        return this;
    }

    public double getVE() {
        return getDouble("v_e");
    }

    public GpsFixRtk setVE(double v_e) {
        values.put("v_e", v_e);
        return this;
    }

    public double getVD() {
        return getDouble("v_d");
    }

    public GpsFixRtk setVD(double v_d) {
        values.put("v_d", v_d);
        return this;
    }

    public short getSatellites() {
        return getShort("satellites");
    }

    public GpsFixRtk setSatellites(short satellites) {
        values.put("satellites", satellites);
        return this;
    }

    public int getIarHyp() {
        return getInteger("iar_hyp");
    }

    public GpsFixRtk setIarHyp(int iar_hyp) {
        values.put("iar_hyp", iar_hyp);
        return this;
    }

    public double getIarRatio() {
        return getDouble("iar_ratio");
    }

    public GpsFixRtk setIarRatio(double iar_ratio) {
        values.put("iar_ratio", iar_ratio);
        return this;
    }

}
