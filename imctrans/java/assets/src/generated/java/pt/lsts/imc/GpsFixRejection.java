package pt.lsts.imc;

public class GpsFixRejection extends IMCMessage {
    public static final int ID_STATIC = 356;


    public enum REASON {
        ABOVE_THRESHOLD(0),
        INVALID(1),
        ABOVE_MAX_HDOP(2),
        ABOVE_MAX_HACC(3),
        LOST_VAL_BIT(4);

        protected long value;

        public long value() {
            return value;
        }

        REASON(long value) {
            this.value = value;
        }
    }

    public GpsFixRejection() {
        super(ID_STATIC);
    }

    public GpsFixRejection(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GpsFixRejection(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GpsFixRejection create(Object... values) {
        GpsFixRejection m = new GpsFixRejection();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GpsFixRejection clone(IMCMessage msg) throws Exception {
        GpsFixRejection m = new GpsFixRejection();
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

    public double getUtcTime() {
        return getDouble("utc_time");
    }

    public GpsFixRejection setUtcTime(double utc_time) {
        values.put("utc_time", utc_time);
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

    public GpsFixRejection setReasonStr(String reason) {
        setValue("reason", reason);
        return this;
    }

    public GpsFixRejection setReasonVal(REASON reason) {
        setValue("reason", reason);
        return this;
    }

    public GpsFixRejection setReason(REASON reason) {
        values.put("reason", reason.value());
        return this;
    }

}
