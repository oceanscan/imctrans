package pt.lsts.imc;

public class LoggingControl extends IMCMessage {
    public static final int ID_STATIC = 102;


    public enum OP {
        REQUEST_START(0),
        STARTED(1),
        REQUEST_STOP(2),
        STOPPED(3),
        REQUEST_CURRENT_NAME(4),
        CURRENT_NAME(5);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public LoggingControl() {
        super(ID_STATIC);
    }

    public LoggingControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LoggingControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LoggingControl create(Object... values) {
        LoggingControl m = new LoggingControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LoggingControl clone(IMCMessage msg) throws Exception {
        LoggingControl m = new LoggingControl();
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

    public OP getOp() {
        try {
            OP o = OP.valueOf(getMessageType().getFieldPossibleValues("op").get(getLong("op")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getOpStr() {
        return getString("op");
    }

    public short getOpVal() {
        return getShort("op");
    }

    public LoggingControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public LoggingControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public LoggingControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getName() {
        return getString("name");
    }

    public LoggingControl setName(String name) {
        values.put("name", name);
        return this;
    }

}
