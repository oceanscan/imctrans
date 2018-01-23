package pt.lsts.imc;

public class Teleoperation extends Maneuver {
    public static final int ID_STATIC = 452;


    public Teleoperation() {
        super(ID_STATIC);
    }

    public Teleoperation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Teleoperation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Teleoperation create(Object... values) {
        Teleoperation m = new Teleoperation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Teleoperation clone(IMCMessage msg) throws Exception {
        Teleoperation m = new Teleoperation();
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

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Teleoperation setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public Teleoperation setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
