package pt.lsts.imc;

public class VehicleMedium extends IMCMessage {
    public static final int ID_STATIC = 508;


    public enum MEDIUM {
        GROUND(0),
        AIR(1),
        WATER(2),
        UNDERWATER(3),
        UNKNOWN(4);

        protected long value;

        public long value() {
            return value;
        }

        MEDIUM(long value) {
            this.value = value;
        }
    }

    public VehicleMedium() {
        super(ID_STATIC);
    }

    public VehicleMedium(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VehicleMedium(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VehicleMedium create(Object... values) {
        VehicleMedium m = new VehicleMedium();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VehicleMedium clone(IMCMessage msg) throws Exception {
        VehicleMedium m = new VehicleMedium();
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

    public MEDIUM getMedium() {
        try {
            MEDIUM o = MEDIUM.valueOf(getMessageType().getFieldPossibleValues("medium").get(getLong("medium")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getMediumStr() {
        return getString("medium");
    }

    public short getMediumVal() {
        return getShort("medium");
    }

    public VehicleMedium setMediumStr(String medium) {
        setValue("medium", medium);
        return this;
    }

    public VehicleMedium setMediumVal(MEDIUM medium) {
        setValue("medium", medium);
        return this;
    }

    public VehicleMedium setMedium(MEDIUM medium) {
        values.put("medium", medium.value());
        return this;
    }

}
