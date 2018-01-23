package pt.lsts.imc;

public class DynamicsSimParam extends IMCMessage {
    public static final int ID_STATIC = 53;


    public enum OP {
        REQUEST(0),
        SET(1),
        REPORT(2);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public DynamicsSimParam() {
        super(ID_STATIC);
    }

    public DynamicsSimParam(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DynamicsSimParam(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DynamicsSimParam create(Object... values) {
        DynamicsSimParam m = new DynamicsSimParam();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DynamicsSimParam clone(IMCMessage msg) throws Exception {
        DynamicsSimParam m = new DynamicsSimParam();
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

    public DynamicsSimParam setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public DynamicsSimParam setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public DynamicsSimParam setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getTas2accPgain() {
        return getDouble("tas2acc_pgain");
    }

    public DynamicsSimParam setTas2accPgain(double tas2acc_pgain) {
        values.put("tas2acc_pgain", tas2acc_pgain);
        return this;
    }

    public double getBank2pPgain() {
        return getDouble("bank2p_pgain");
    }

    public DynamicsSimParam setBank2pPgain(double bank2p_pgain) {
        values.put("bank2p_pgain", bank2p_pgain);
        return this;
    }

}
