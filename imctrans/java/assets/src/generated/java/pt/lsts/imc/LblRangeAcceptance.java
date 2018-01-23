package pt.lsts.imc;

public class LblRangeAcceptance extends IMCMessage {
    public static final int ID_STATIC = 357;


    public enum ACCEPTANCE {
        ACCEPTED(0),
        ABOVE_THRESHOLD(1),
        SINGULAR(2),
        NO_INFO(3),
        AT_SURFACE(4);

        protected long value;

        public long value() {
            return value;
        }

        ACCEPTANCE(long value) {
            this.value = value;
        }
    }

    public LblRangeAcceptance() {
        super(ID_STATIC);
    }

    public LblRangeAcceptance(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LblRangeAcceptance(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LblRangeAcceptance create(Object... values) {
        LblRangeAcceptance m = new LblRangeAcceptance();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LblRangeAcceptance clone(IMCMessage msg) throws Exception {
        LblRangeAcceptance m = new LblRangeAcceptance();
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

    public short getId() {
        return getShort("id");
    }

    public LblRangeAcceptance setId(short id) {
        values.put("id", id);
        return this;
    }

    public double getRange() {
        return getDouble("range");
    }

    public LblRangeAcceptance setRange(double range) {
        values.put("range", range);
        return this;
    }

    public ACCEPTANCE getAcceptance() {
        try {
            ACCEPTANCE o = ACCEPTANCE.valueOf(getMessageType().getFieldPossibleValues("acceptance").get(getLong("acceptance")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getAcceptanceStr() {
        return getString("acceptance");
    }

    public short getAcceptanceVal() {
        return getShort("acceptance");
    }

    public LblRangeAcceptance setAcceptanceStr(String acceptance) {
        setValue("acceptance", acceptance);
        return this;
    }

    public LblRangeAcceptance setAcceptanceVal(ACCEPTANCE acceptance) {
        setValue("acceptance", acceptance);
        return this;
    }

    public LblRangeAcceptance setAcceptance(ACCEPTANCE acceptance) {
        values.put("acceptance", acceptance.value());
        return this;
    }

}
