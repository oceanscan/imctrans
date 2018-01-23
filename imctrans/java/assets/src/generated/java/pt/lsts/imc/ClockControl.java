package pt.lsts.imc;

public class ClockControl extends IMCMessage {
    public static final int ID_STATIC = 106;


    public enum OP {
        SYNC_EXEC(0),
        SYNC_REQUEST(1),
        SYNC_STARTED(2),
        SYNC_DONE(3),
        SET_TZ(4),
        SET_TZ_DONE(5);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public ClockControl() {
        super(ID_STATIC);
    }

    public ClockControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ClockControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ClockControl create(Object... values) {
        ClockControl m = new ClockControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ClockControl clone(IMCMessage msg) throws Exception {
        ClockControl m = new ClockControl();
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

    public ClockControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public ClockControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public ClockControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getClock() {
        return getDouble("clock");
    }

    public ClockControl setClock(double clock) {
        values.put("clock", clock);
        return this;
    }

    public byte getTz() {
        return getByte("tz");
    }

    public ClockControl setTz(byte tz) {
        values.put("tz", tz);
        return this;
    }

}
