package pt.lsts.imc;

public class MonitorEntityState extends IMCMessage {
    public static final int ID_STATIC = 502;


    public enum COMMAND {
        RESET(0),
        ENABLE(1),
        DISABLE(2),
        ENABLE_EXCLUSIVE(3),
        STATUS(4);

        protected long value;

        public long value() {
            return value;
        }

        COMMAND(long value) {
            this.value = value;
        }
    }

    public MonitorEntityState() {
        super(ID_STATIC);
    }

    public MonitorEntityState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public MonitorEntityState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MonitorEntityState create(Object... values) {
        MonitorEntityState m = new MonitorEntityState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static MonitorEntityState clone(IMCMessage msg) throws Exception {
        MonitorEntityState m = new MonitorEntityState();
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

    public MonitorEntityState setCommandStr(String command) {
        setValue("command", command);
        return this;
    }

    public MonitorEntityState setCommandVal(COMMAND command) {
        setValue("command", command);
        return this;
    }

    public MonitorEntityState setCommand(COMMAND command) {
        values.put("command", command.value());
        return this;
    }

    public String getEntities() {
        return getString("entities");
    }

    public MonitorEntityState setEntities(String entities) {
        values.put("entities", entities);
        return this;
    }

}
