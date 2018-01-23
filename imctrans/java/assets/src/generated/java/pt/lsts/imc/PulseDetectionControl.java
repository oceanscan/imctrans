package pt.lsts.imc;

public class PulseDetectionControl extends IMCMessage {
    public static final int ID_STATIC = 278;


    public enum OP {
        OFF(0),
        ON(1);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public PulseDetectionControl() {
        super(ID_STATIC);
    }

    public PulseDetectionControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PulseDetectionControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PulseDetectionControl create(Object... values) {
        PulseDetectionControl m = new PulseDetectionControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PulseDetectionControl clone(IMCMessage msg) throws Exception {
        PulseDetectionControl m = new PulseDetectionControl();
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

    public PulseDetectionControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public PulseDetectionControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public PulseDetectionControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

}
