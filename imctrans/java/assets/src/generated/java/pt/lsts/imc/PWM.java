package pt.lsts.imc;

public class PWM extends IMCMessage {
    public static final int ID_STATIC = 316;


    public PWM() {
        super(ID_STATIC);
    }

    public PWM(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PWM(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PWM create(Object... values) {
        PWM m = new PWM();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PWM clone(IMCMessage msg) throws Exception {
        PWM m = new PWM();
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

    public short getId() {
        return getShort("id");
    }

    public PWM setId(short id) {
        values.put("id", id);
        return this;
    }

    public long getPeriod() {
        return getLong("period");
    }

    public PWM setPeriod(long period) {
        values.put("period", period);
        return this;
    }

    public long getDutyCycle() {
        return getLong("duty_cycle");
    }

    public PWM setDutyCycle(long duty_cycle) {
        values.put("duty_cycle", duty_cycle);
        return this;
    }

}
