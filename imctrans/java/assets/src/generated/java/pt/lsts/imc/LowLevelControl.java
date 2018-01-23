package pt.lsts.imc;

public class LowLevelControl extends Maneuver {
    public static final int ID_STATIC = 455;


    public LowLevelControl() {
        super(ID_STATIC);
    }

    public LowLevelControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LowLevelControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LowLevelControl create(Object... values) {
        LowLevelControl m = new LowLevelControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LowLevelControl clone(IMCMessage msg) throws Exception {
        LowLevelControl m = new LowLevelControl();
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

    public ControlCommand getControl() {
        return getMessageOrNull(ControlCommand.class, "ControlCommand");
    }

    public <T extends IMCMessage> T getControl(Class<T> clazz) throws Exception {
        return getMessage(clazz, "control");
    }

    public LowLevelControl setControl(ControlCommand control) {
        values.put("control", control);
        return this;
    }

    public int getDuration() {
        return getInteger("duration");
    }

    public LowLevelControl setDuration(int duration) {
        values.put("duration", duration);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public LowLevelControl setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public LowLevelControl setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
