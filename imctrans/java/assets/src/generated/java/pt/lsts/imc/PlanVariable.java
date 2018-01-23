package pt.lsts.imc;

public class PlanVariable extends IMCMessage {
    public static final int ID_STATIC = 561;


    public enum TYPE {
        BOOLEAN(0),
        NUMBER(1),
        TEXT(2),
        MESSAGE(3);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public enum ACCESS {
        INPUT(0),
        OUTPUT(1),
        LOCAL(2);

        protected long value;

        public long value() {
            return value;
        }

        ACCESS(long value) {
            this.value = value;
        }
    }

    public PlanVariable() {
        super(ID_STATIC);
    }

    public PlanVariable(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanVariable(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanVariable create(Object... values) {
        PlanVariable m = new PlanVariable();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanVariable clone(IMCMessage msg) throws Exception {
        PlanVariable m = new PlanVariable();
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

    public String getName() {
        return getString("name");
    }

    public PlanVariable setName(String name) {
        values.put("name", name);
        return this;
    }

    public String getValue() {
        return getString("value");
    }

    public PlanVariable setValue(String value) {
        values.put("value", value);
        return this;
    }

    public TYPE getType() {
        try {
            TYPE o = TYPE.valueOf(getMessageType().getFieldPossibleValues("type").get(getLong("type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTypeStr() {
        return getString("type");
    }

    public short getTypeVal() {
        return getShort("type");
    }

    public PlanVariable setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public PlanVariable setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public PlanVariable setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public ACCESS getAccess() {
        try {
            ACCESS o = ACCESS.valueOf(getMessageType().getFieldPossibleValues("access").get(getLong("access")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAccessStr() {
        return getString("access");
    }

    public short getAccessVal() {
        return getShort("access");
    }

    public PlanVariable setAccessStr(String access) {
        setValue("access", access);
        return this;
    }

    public PlanVariable setAccessVal(ACCESS access) {
        setValue("access", access);
        return this;
    }

    public PlanVariable setAccess(ACCESS access) {
        values.put("access", access.value());
        return this;
    }

}
