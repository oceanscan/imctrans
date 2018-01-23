package pt.lsts.imc;

public class LcdControl extends IMCMessage {
    public static final int ID_STATIC = 307;


    public enum OP {
        TURN_OFF(0),
        TURN_ON(1),
        CLEAR(2),
        WRITE0(3),
        WRITE1(4);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public LcdControl() {
        super(ID_STATIC);
    }

    public LcdControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LcdControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LcdControl create(Object... values) {
        LcdControl m = new LcdControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LcdControl clone(IMCMessage msg) throws Exception {
        LcdControl m = new LcdControl();
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

    public LcdControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public LcdControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public LcdControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getText() {
        return getString("text");
    }

    public LcdControl setText(String text) {
        values.put("text", text);
        return this;
    }

}
