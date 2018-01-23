package pt.lsts.imc;

public class ReplayControl extends IMCMessage {
    public static final int ID_STATIC = 105;


    public enum OP {
        START(0),
        STOP(1),
        PAUSE(2),
        RESUME(3);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public ReplayControl() {
        super(ID_STATIC);
    }

    public ReplayControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ReplayControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ReplayControl create(Object... values) {
        ReplayControl m = new ReplayControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ReplayControl clone(IMCMessage msg) throws Exception {
        ReplayControl m = new ReplayControl();
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

    public ReplayControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public ReplayControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public ReplayControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getFile() {
        return getString("file");
    }

    public ReplayControl setFile(String file) {
        values.put("file", file);
        return this;
    }

}
