package pt.lsts.imc;

public class HistoricCTD extends IMCMessage {
    public static final int ID_STATIC = 107;


    public HistoricCTD() {
        super(ID_STATIC);
    }

    public HistoricCTD(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public HistoricCTD(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HistoricCTD create(Object... values) {
        HistoricCTD m = new HistoricCTD();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static HistoricCTD clone(IMCMessage msg) throws Exception {
        HistoricCTD m = new HistoricCTD();
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

    public double getConductivity() {
        return getDouble("conductivity");
    }

    public HistoricCTD setConductivity(double conductivity) {
        values.put("conductivity", conductivity);
        return this;
    }

    public double getTemperature() {
        return getDouble("temperature");
    }

    public HistoricCTD setTemperature(double temperature) {
        values.put("temperature", temperature);
        return this;
    }

    public double getDepth() {
        return getDouble("depth");
    }

    public HistoricCTD setDepth(double depth) {
        values.put("depth", depth);
        return this;
    }

}
