package pt.lsts.imc;

public class IdleManeuver extends Maneuver {
    public static final int ID_STATIC = 454;


    public IdleManeuver() {
        super(ID_STATIC);
    }

    public IdleManeuver(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public IdleManeuver(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IdleManeuver create(Object... values) {
        IdleManeuver m = new IdleManeuver();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static IdleManeuver clone(IMCMessage msg) throws Exception {
        IdleManeuver m = new IdleManeuver();
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

    public int getDuration() {
        return getInteger("duration");
    }

    public IdleManeuver setDuration(int duration) {
        values.put("duration", duration);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public IdleManeuver setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public IdleManeuver setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
