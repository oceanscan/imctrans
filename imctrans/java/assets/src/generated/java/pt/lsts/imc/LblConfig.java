package pt.lsts.imc;

public class LblConfig extends IMCMessage {
    public static final int ID_STATIC = 203;


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

    public LblConfig() {
        super(ID_STATIC);
    }

    public LblConfig(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LblConfig(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LblConfig create(Object... values) {
        LblConfig m = new LblConfig();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LblConfig clone(IMCMessage msg) throws Exception {
        LblConfig m = new LblConfig();
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

    public LblConfig setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public LblConfig setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public LblConfig setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public java.util.Vector<LblBeacon> getBeacons() {
        return getMessageListOrNull("beacons", LblBeacon.class);
    }

    public LblConfig setBeacons(java.util.Collection<LblBeacon> beacons) {
        values.put("beacons", beacons);
        return this;
    }

}
