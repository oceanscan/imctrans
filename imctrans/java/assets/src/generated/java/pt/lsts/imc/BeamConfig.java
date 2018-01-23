package pt.lsts.imc;

public class BeamConfig extends IMCMessage {
    public static final int ID_STATIC = 283;


    public BeamConfig() {
        super(ID_STATIC);
    }

    public BeamConfig(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public BeamConfig(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BeamConfig create(Object... values) {
        BeamConfig m = new BeamConfig();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static BeamConfig clone(IMCMessage msg) throws Exception {
        BeamConfig m = new BeamConfig();
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

    public double getBeamWidth() {
        return getDouble("beam_width");
    }

    public BeamConfig setBeamWidth(double beam_width) {
        values.put("beam_width", beam_width);
        return this;
    }

    public double getBeamHeight() {
        return getDouble("beam_height");
    }

    public BeamConfig setBeamHeight(double beam_height) {
        values.put("beam_height", beam_height);
        return this;
    }

}
