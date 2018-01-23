package pt.lsts.imc;

public class CustomManeuver extends Maneuver {
    public static final int ID_STATIC = 465;


    public CustomManeuver() {
        super(ID_STATIC);
    }

    public CustomManeuver(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CustomManeuver(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CustomManeuver create(Object... values) {
        CustomManeuver m = new CustomManeuver();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CustomManeuver clone(IMCMessage msg) throws Exception {
        CustomManeuver m = new CustomManeuver();
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

    public int getTimeout() {
        return getInteger("timeout");
    }

    public CustomManeuver setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public String getName() {
        return getString("name");
    }

    public CustomManeuver setName(String name) {
        values.put("name", name);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public CustomManeuver setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public CustomManeuver setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
