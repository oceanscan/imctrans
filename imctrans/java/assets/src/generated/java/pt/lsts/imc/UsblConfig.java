package pt.lsts.imc;

public class UsblConfig extends IMCMessage {
    public static final int ID_STATIC = 902;


    public enum OP {
        SET_CFG(0),
        GET_CFG(1),
        CUR_CFG(2);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public UsblConfig() {
        super(ID_STATIC);
    }

    public UsblConfig(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblConfig(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblConfig create(Object... values) {
        UsblConfig m = new UsblConfig();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblConfig clone(IMCMessage msg) throws Exception {
        UsblConfig m = new UsblConfig();
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

    public UsblConfig setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public UsblConfig setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public UsblConfig setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public java.util.Vector<UsblModem> getModems() {
        return getMessageListOrNull("modems", UsblModem.class);
    }

    public UsblConfig setModems(java.util.Collection<UsblModem> modems) {
        values.put("modems", modems);
        return this;
    }

}
