package pt.lsts.imc;

public class MapFeature extends IMCMessage {
    public static final int ID_STATIC = 603;


    public enum FEATURE_TYPE {
        POI(0),
        FILLEDPOLY(1),
        CONTOUREDPOLY(2),
        LINE(3),
        TRANSPONDER(4),
        STARTLOC(5),
        HOMEREF(6);

        protected long value;

        public long value() {
            return value;
        }

        FEATURE_TYPE(long value) {
            this.value = value;
        }
    }

    public MapFeature() {
        super(ID_STATIC);
    }

    public MapFeature(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public MapFeature(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MapFeature create(Object... values) {
        MapFeature m = new MapFeature();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static MapFeature clone(IMCMessage msg) throws Exception {
        MapFeature m = new MapFeature();
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

    public String getId() {
        return getString("id");
    }

    public MapFeature setId(String id) {
        values.put("id", id);
        return this;
    }

    public FEATURE_TYPE getFeatureType() {
        try {
            FEATURE_TYPE o = FEATURE_TYPE.valueOf(getMessageType().getFieldPossibleValues("feature_type").get(getLong("feature_type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getFeatureTypeStr() {
        return getString("feature_type");
    }

    public short getFeatureTypeVal() {
        return getShort("feature_type");
    }

    public MapFeature setFeatureTypeStr(String feature_type) {
        setValue("feature_type", feature_type);
        return this;
    }

    public MapFeature setFeatureTypeVal(FEATURE_TYPE feature_type) {
        setValue("feature_type", feature_type);
        return this;
    }

    public MapFeature setFeatureType(FEATURE_TYPE feature_type) {
        values.put("feature_type", feature_type.value());
        return this;
    }

    public short getRgbRed() {
        return getShort("rgb_red");
    }

    public MapFeature setRgbRed(short rgb_red) {
        values.put("rgb_red", rgb_red);
        return this;
    }

    public short getRgbGreen() {
        return getShort("rgb_green");
    }

    public MapFeature setRgbGreen(short rgb_green) {
        values.put("rgb_green", rgb_green);
        return this;
    }

    public short getRgbBlue() {
        return getShort("rgb_blue");
    }

    public MapFeature setRgbBlue(short rgb_blue) {
        values.put("rgb_blue", rgb_blue);
        return this;
    }

    public java.util.Vector<MapPoint> getFeature() {
        return getMessageListOrNull("feature", MapPoint.class);
    }

    public MapFeature setFeature(java.util.Collection<MapPoint> feature) {
        values.put("feature", feature);
        return this;
    }

}
