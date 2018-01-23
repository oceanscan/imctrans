package pt.lsts.imc;

public class TrexOperation extends IMCMessage {
    public static final int ID_STATIC = 655;


    public enum OP {
        POST_TOKEN(1),
        POST_GOAL(2),
        RECALL_GOAL(3),
        REQUEST_PLAN(4),
        REPORT_PLAN(5);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public TrexOperation() {
        super(ID_STATIC);
    }

    public TrexOperation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TrexOperation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrexOperation create(Object... values) {
        TrexOperation m = new TrexOperation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TrexOperation clone(IMCMessage msg) throws Exception {
        TrexOperation m = new TrexOperation();
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

    public TrexOperation setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public TrexOperation setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public TrexOperation setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getGoalId() {
        return getString("goal_id");
    }

    public TrexOperation setGoalId(String goal_id) {
        values.put("goal_id", goal_id);
        return this;
    }

    public TrexToken getToken() {
        return getMessageOrNull(TrexToken.class, "TrexToken");
    }

    public <T extends IMCMessage> T getToken(Class<T> clazz) throws Exception {
        return getMessage(clazz, "token");
    }

    public TrexOperation setToken(TrexToken token) {
        values.put("token", token);
        return this;
    }

}
