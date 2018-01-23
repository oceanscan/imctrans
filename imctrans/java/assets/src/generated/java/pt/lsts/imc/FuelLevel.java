package pt.lsts.imc;

public class FuelLevel extends IMCMessage {
    public static final int ID_STATIC = 279;


    public FuelLevel() {
        super(ID_STATIC);
    }

    public FuelLevel(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FuelLevel(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FuelLevel create(Object... values) {
        FuelLevel m = new FuelLevel();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FuelLevel clone(IMCMessage msg) throws Exception {
        FuelLevel m = new FuelLevel();
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

    public double getValue() {
        return getDouble("value");
    }

    public FuelLevel setValue(double value) {
        values.put("value", value);
        return this;
    }

    public double getConfidence() {
        return getDouble("confidence");
    }

    public FuelLevel setConfidence(double confidence) {
        values.put("confidence", confidence);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getOpmodes() {
        return getTupleList("opmodes");
    }

    public FuelLevel setOpmodes(java.util.LinkedHashMap<String, ?> opmodes) {
        return setOpmodes(opmodes);
    }

    public FuelLevel setOpmodes(String opmodes) {
        values.put("opmodes", opmodes);
        return this;
    }

}
