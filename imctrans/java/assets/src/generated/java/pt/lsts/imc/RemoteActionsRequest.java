package pt.lsts.imc;

public class RemoteActionsRequest extends IMCMessage {
    public static final int ID_STATIC = 304;


    public enum OP {
        REPORT(0),
        QUERY(1);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public RemoteActionsRequest() {
        super(ID_STATIC);
    }

    public RemoteActionsRequest(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RemoteActionsRequest(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RemoteActionsRequest create(Object... values) {
        RemoteActionsRequest m = new RemoteActionsRequest();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RemoteActionsRequest clone(IMCMessage msg) throws Exception {
        RemoteActionsRequest m = new RemoteActionsRequest();
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

    public RemoteActionsRequest setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public RemoteActionsRequest setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public RemoteActionsRequest setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getActions() {
        return getTupleList("actions");
    }

    public RemoteActionsRequest setActions(java.util.LinkedHashMap<String, ?> actions) {
        values.put("actions", encodeTupleList(actions));
        return this;
    }

    public RemoteActionsRequest setActions(String actions) {
        values.put("actions", actions);
        return this;
    }

}
