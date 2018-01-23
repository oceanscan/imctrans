package pt.lsts.imc;

public class HistoricSonarData extends IMCMessage {
    public static final int ID_STATIC = 109;


    public enum ENCODING {
        ONE_BYTE_PER_PIXEL(0),
        PNG(1),
        JPEG(2);

        protected long value;

        public long value() {
            return value;
        }

        ENCODING(long value) {
            this.value = value;
        }
    }

    public HistoricSonarData() {
        super(ID_STATIC);
    }

    public HistoricSonarData(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public HistoricSonarData(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HistoricSonarData create(Object... values) {
        HistoricSonarData m = new HistoricSonarData();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static HistoricSonarData clone(IMCMessage msg) throws Exception {
        HistoricSonarData m = new HistoricSonarData();
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

    public double getAltitude() {
        return getDouble("altitude");
    }

    public HistoricSonarData setAltitude(double altitude) {
        values.put("altitude", altitude);
        return this;
    }

    public double getWidth() {
        return getDouble("width");
    }

    public HistoricSonarData setWidth(double width) {
        values.put("width", width);
        return this;
    }

    public double getLength() {
        return getDouble("length");
    }

    public HistoricSonarData setLength(double length) {
        values.put("length", length);
        return this;
    }

    public double getBearing() {
        return getDouble("bearing");
    }

    public HistoricSonarData setBearing(double bearing) {
        values.put("bearing", bearing);
        return this;
    }

    public short getPxl() {
        return getShort("pxl");
    }

    public HistoricSonarData setPxl(short pxl) {
        values.put("pxl", pxl);
        return this;
    }

    public ENCODING getEncoding() {
        try {
            ENCODING o = ENCODING.valueOf(getMessageType().getFieldPossibleValues("encoding").get(getLong("encoding")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getEncodingStr() {
        return getString("encoding");
    }

    public short getEncodingVal() {
        return getShort("encoding");
    }

    public HistoricSonarData setEncodingStr(String encoding) {
        setValue("encoding", encoding);
        return this;
    }

    public HistoricSonarData setEncodingVal(ENCODING encoding) {
        setValue("encoding", encoding);
        return this;
    }

    public HistoricSonarData setEncoding(ENCODING encoding) {
        values.put("encoding", encoding.value());
        return this;
    }

    public byte[] getSonarData() {
        return getRawData("sonar_data");
    }

    public HistoricSonarData setSonarData(byte[] sonar_data) {
        values.put("sonar_data", sonar_data);
        return this;
    }

}
