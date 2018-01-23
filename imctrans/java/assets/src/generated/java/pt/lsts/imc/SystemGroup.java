package pt.lsts.imc;

public class SystemGroup extends IMCMessage {
    public static final int ID_STATIC = 181;


    public enum ACTION {
        Dis(0),
        Set(1),
        Req(2),
        Chg(3),
        Rep(4),
        Frc(5);

        protected long value;

        public long value() {
            return value;
        }

        ACTION(long value) {
            this.value = value;
        }
    }

    public SystemGroup() {
        super(ID_STATIC);
    }

    public SystemGroup(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SystemGroup(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SystemGroup create(Object... values) {
        SystemGroup m = new SystemGroup();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SystemGroup clone(IMCMessage msg) throws Exception {
        SystemGroup m = new SystemGroup();
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

    public String getGroupname() {
        return getString("GroupName");
    }

    public SystemGroup setGroupname(String GroupName) {
        values.put("GroupName", GroupName);
        return this;
    }

    public ACTION getAction() {
        try {
            ACTION o = ACTION.valueOf(getMessageType().getFieldPossibleValues("Action").get(getLong("Action")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getActionStr() {
        return getString("Action");
    }

    public short getActionVal() {
        return getShort("Action");
    }

    public SystemGroup setActionStr(String Action) {
        setValue("Action", Action);
        return this;
    }

    public SystemGroup setActionVal(ACTION Action) {
        setValue("Action", Action);
        return this;
    }

    public SystemGroup setAction(ACTION Action) {
        values.put("Action", Action.value());
        return this;
    }

    public String getGrouplist() {
        return getString("GroupList");
    }

    public SystemGroup setGrouplist(String GroupList) {
        values.put("GroupList", GroupList);
        return this;
    }

}
