package pt.lsts.imc;

public class AutopilotMode extends IMCMessage {
    public static final int ID_STATIC = 511;


    public enum AUTONOMY {
        MANUAL(0),
        ASSISTED(1),
        AUTO(2);

        protected long value;

        public long value() {
            return value;
        }

        AUTONOMY(long value) {
            this.value = value;
        }
    }

    public AutopilotMode() {
        super(ID_STATIC);
    }

    public AutopilotMode(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AutopilotMode(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AutopilotMode create(Object... values) {
        AutopilotMode m = new AutopilotMode();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AutopilotMode clone(IMCMessage msg) throws Exception {
        AutopilotMode m = new AutopilotMode();
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

    public AUTONOMY getAutonomy() {
        try {
            AUTONOMY o = AUTONOMY.valueOf(getMessageType().getFieldPossibleValues("autonomy").get(getLong("autonomy")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAutonomyStr() {
        return getString("autonomy");
    }

    public short getAutonomyVal() {
        return getShort("autonomy");
    }

    public AutopilotMode setAutonomyStr(String autonomy) {
        setValue("autonomy", autonomy);
        return this;
    }

    public AutopilotMode setAutonomyVal(AUTONOMY autonomy) {
        setValue("autonomy", autonomy);
        return this;
    }

    public AutopilotMode setAutonomy(AUTONOMY autonomy) {
        values.put("autonomy", autonomy.value());
        return this;
    }

    public String getMode() {
        return getString("mode");
    }

    public AutopilotMode setMode(String mode) {
        values.put("mode", mode);
        return this;
    }

}
