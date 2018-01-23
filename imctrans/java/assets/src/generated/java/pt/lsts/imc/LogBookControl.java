package pt.lsts.imc;

public class LogBookControl extends IMCMessage {
    public static final int ID_STATIC = 104;


    public enum COMMAND {
        GET(0),
        CLEAR(1),
        GET_ERR(2),
        REPLY(3);

        protected long value;

        public long value() {
            return value;
        }

        COMMAND(long value) {
            this.value = value;
        }
    }

    public LogBookControl() {
        super(ID_STATIC);
    }

    public LogBookControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LogBookControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LogBookControl create(Object... values) {
        LogBookControl m = new LogBookControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LogBookControl clone(IMCMessage msg) throws Exception {
        LogBookControl m = new LogBookControl();
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

    public COMMAND getCommand() {
        try {
            COMMAND o = COMMAND.valueOf(getMessageType().getFieldPossibleValues("command").get(getLong("command")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getCommandStr() {
        return getString("command");
    }

    public short getCommandVal() {
        return getShort("command");
    }

    public LogBookControl setCommandStr(String command) {
        setValue("command", command);
        return this;
    }

    public LogBookControl setCommandVal(COMMAND command) {
        setValue("command", command);
        return this;
    }

    public LogBookControl setCommand(COMMAND command) {
        values.put("command", command.value());
        return this;
    }

    public double getHtime() {
        return getDouble("htime");
    }

    public LogBookControl setHtime(double htime) {
        values.put("htime", htime);
        return this;
    }

    public java.util.Vector<LogBookEntry> getMsg() {
        return getMessageListOrNull("msg", LogBookEntry.class);
    }

    public LogBookControl setMsg(java.util.Collection<LogBookEntry> msg) {
        values.put("msg", msg);
        return this;
    }

}
