package pt.lsts.imc;

public class Map extends IMCMessage {
    public static final int ID_STATIC = 602;


    public Map() {
        super(ID_STATIC);
    }

    public Map(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Map(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map create(Object... values) {
        Map m = new Map();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Map clone(IMCMessage msg) throws Exception {
        Map m = new Map();
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

    public Map setId(String id) {
        values.put("id", id);
        return this;
    }

    public java.util.Vector<MapFeature> getFeatures() {
        return getMessageListOrNull("features", MapFeature.class);
    }

    public Map setFeatures(java.util.Collection<MapFeature> features) {
        values.put("features", features);
        return this;
    }

}
