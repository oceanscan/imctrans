package pt.lsts.imc;

public class TrexCommand extends IMCMessage {
    public static final int ID_STATIC = 652;


    public enum COMMAND {
        DISABLE(0),
        ENABLE(1),
        POST_GOAL(2),
        RECALL_GOAL(3),
        REQUEST_PLAN(4),
        REPORT_PLAN(5);

        protected long value;

        public long value() {
            return value;
        }

        COMMAND(long value) {
            this.value = value;
        }
    }

    public TrexCommand() {
        super(ID_STATIC);
    }

    public TrexCommand(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TrexCommand(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrexCommand create(Object... values) {
        TrexCommand m = new TrexCommand();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TrexCommand clone(IMCMessage msg) throws Exception {
        TrexCommand m = new TrexCommand();
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

    public TrexCommand setCommandStr(String command) {
        setValue("command", command);
        return this;
    }

    public TrexCommand setCommandVal(COMMAND command) {
        setValue("command", command);
        return this;
    }

    public TrexCommand setCommand(COMMAND command) {
        values.put("command", command.value());
        return this;
    }

    public String getGoalId() {
        return getString("goal_id");
    }

    public TrexCommand setGoalId(String goal_id) {
        values.put("goal_id", goal_id);
        return this;
    }

    public String getGoalXml() {
        return getString("goal_xml");
    }

    public TrexCommand setGoalXml(String goal_xml) {
        values.put("goal_xml", goal_xml);
        return this;
    }

}
