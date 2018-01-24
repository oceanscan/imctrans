package pt.lsts.imc;

public class EntityList extends IMCMessage {
    public static final int ID_STATIC = 5;


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

    public EntityList() {
        super(ID_STATIC);
    }

    public EntityList(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public EntityList(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityList create(Object... values) {
        EntityList m = new EntityList();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static EntityList clone(IMCMessage msg) throws Exception {
        EntityList m = new EntityList();
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

    public EntityList setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public EntityList setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public EntityList setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getList() {
        return getTupleList("list");
    }

    public EntityList setList(java.util.LinkedHashMap<String, ?> list) {
        values.put("list", encodeTupleList(list));
        return this;
    }

    public EntityList setList(String list) {
        values.put("list", list);
        return this;
    }

}
