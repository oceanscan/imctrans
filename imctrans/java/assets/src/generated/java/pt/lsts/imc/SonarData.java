package pt.lsts.imc;

public class SonarData extends IMCMessage {
    public static final int ID_STATIC = 276;


    public enum TYPE {
        SIDESCAN(0),
        ECHOSOUNDER(1),
        MULTIBEAM(2);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public SonarData() {
        super(ID_STATIC);
    }

    public SonarData(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SonarData(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SonarData create(Object... values) {
        SonarData m = new SonarData();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SonarData clone(IMCMessage msg) throws Exception {
        SonarData m = new SonarData();
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

    public SonarData setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public SonarData setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public SonarData setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public long getFrequency() {
        return getLong("frequency");
    }

    public SonarData setFrequency(long frequency) {
        values.put("frequency", frequency);
        return this;
    }

    public int getMinRange() {
        return getInteger("min_range");
    }

    public SonarData setMinRange(int min_range) {
        values.put("min_range", min_range);
        return this;
    }

    public int getMaxRange() {
        return getInteger("max_range");
    }

    public SonarData setMaxRange(int max_range) {
        values.put("max_range", max_range);
        return this;
    }

    public short getBitsPerPoint() {
        return getShort("bits_per_point");
    }

    public SonarData setBitsPerPoint(short bits_per_point) {
        values.put("bits_per_point", bits_per_point);
        return this;
    }

    public double getScaleFactor() {
        return getDouble("scale_factor");
    }

    public SonarData setScaleFactor(double scale_factor) {
        values.put("scale_factor", scale_factor);
        return this;
    }

    public java.util.Vector<BeamConfig> getBeamConfig() {
        return getMessageListOrNull("beam_config", BeamConfig.class);
    }

    public SonarData setBeamConfig(java.util.Collection<BeamConfig> beam_config) {
        values.put("beam_config", beam_config);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public SonarData setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
