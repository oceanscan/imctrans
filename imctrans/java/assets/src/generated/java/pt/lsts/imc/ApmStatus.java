package pt.lsts.imc;

public class ApmStatus extends IMCMessage {
    public static final int ID_STATIC = 906;


    public enum SEVERITY {
        EMERGENCY(0),
        ALERT(1),
        CRITICAL(2),
        ERROR(3),
        WARNING(4),
        NOTICE(5),
        INFO(6),
        DEBUG(7);

        protected long value;

        public long value() {
            return value;
        }

        SEVERITY(long value) {
            this.value = value;
        }
    }

    public ApmStatus() {
        super(ID_STATIC);
    }

    public ApmStatus(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ApmStatus(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ApmStatus create(Object... values) {
        ApmStatus m = new ApmStatus();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ApmStatus clone(IMCMessage msg) throws Exception {
        ApmStatus m = new ApmStatus();
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

    public SEVERITY getSeverity() {
        try {
            SEVERITY o = SEVERITY.valueOf(getMessageType().getFieldPossibleValues("severity").get(getLong("severity")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSeverityStr() {
        return getString("severity");
    }

    public short getSeverityVal() {
        return getShort("severity");
    }

    public ApmStatus setSeverityStr(String severity) {
        setValue("severity", severity);
        return this;
    }

    public ApmStatus setSeverityVal(SEVERITY severity) {
        setValue("severity", severity);
        return this;
    }

    public ApmStatus setSeverity(SEVERITY severity) {
        values.put("severity", severity.value());
        return this;
    }

    public String getText() {
        return getString("text");
    }

    public ApmStatus setText(String text) {
        values.put("text", text);
        return this;
    }

}
