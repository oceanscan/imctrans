package pt.lsts.imc;

public class DvlRejection extends IMCMessage {
    public static final int ID_STATIC = 358;

public static final short TYPE_GV = 0x01;
public static final short TYPE_WV = 0x02;

    public enum REASON {
        INNOV_THRESHOLD_X(0),
        INNOV_THRESHOLD_Y(1),
        ABS_THRESHOLD_X(2),
        ABS_THRESHOLD_Y(3);

        protected long value;

        public long value() {
            return value;
        }

        REASON(long value) {
            this.value = value;
        }
    }

    public DvlRejection() {
        super(ID_STATIC);
    }

    public DvlRejection(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DvlRejection(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DvlRejection create(Object... values) {
        DvlRejection m = new DvlRejection();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DvlRejection clone(IMCMessage msg) throws Exception {
        DvlRejection m = new DvlRejection();
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

    public short getType() {
        return getShort("type");
    }

    public DvlRejection setType(short type) {
        values.put("type", type);
        return this;
    }

    public REASON getReason() {
        try {
            REASON o = REASON.valueOf(getMessageType().getFieldPossibleValues("reason").get(getLong("reason")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getReasonStr() {
        return getString("reason");
    }

    public short getReasonVal() {
        return getShort("reason");
    }

    public DvlRejection setReasonStr(String reason) {
        setValue("reason", reason);
        return this;
    }

    public DvlRejection setReasonVal(REASON reason) {
        setValue("reason", reason);
        return this;
    }

    public DvlRejection setReason(REASON reason) {
        values.put("reason", reason.value());
        return this;
    }

    public double getValue() {
        return getDouble("value");
    }

    public DvlRejection setValue(double value) {
        values.put("value", value);
        return this;
    }

    public double getTimestep() {
        return getDouble("timestep");
    }

    public DvlRejection setTimestep(double timestep) {
        values.put("timestep", timestep);
        return this;
    }

}
