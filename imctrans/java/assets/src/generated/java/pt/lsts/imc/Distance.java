package pt.lsts.imc;

public class Distance extends IMCMessage {
    public static final int ID_STATIC = 262;


    public enum VALIDITY {
        INVALID(0),
        VALID(1);

        protected long value;

        public long value() {
            return value;
        }

        VALIDITY(long value) {
            this.value = value;
        }
    }

    public Distance() {
        super(ID_STATIC);
    }

    public Distance(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Distance(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Distance create(Object... values) {
        Distance m = new Distance();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Distance clone(IMCMessage msg) throws Exception {
        Distance m = new Distance();
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

    public VALIDITY getValidity() {
        try {
            VALIDITY o = VALIDITY.valueOf(getMessageType().getFieldPossibleValues("validity").get(getLong("validity")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getValidityStr() {
        return getString("validity");
    }

    public short getValidityVal() {
        return getShort("validity");
    }

    public Distance setValidityStr(String validity) {
        setValue("validity", validity);
        return this;
    }

    public Distance setValidityVal(VALIDITY validity) {
        setValue("validity", validity);
        return this;
    }

    public Distance setValidity(VALIDITY validity) {
        values.put("validity", validity.value());
        return this;
    }

    public java.util.Vector<DeviceState> getLocation() {
        return getMessageListOrNull("location", DeviceState.class);
    }

    public Distance setLocation(java.util.Collection<DeviceState> location) {
        values.put("location", location);
        return this;
    }

    public java.util.Vector<BeamConfig> getBeamConfig() {
        return getMessageListOrNull("beam_config", BeamConfig.class);
    }

    public Distance setBeamConfig(java.util.Collection<BeamConfig> beam_config) {
        values.put("beam_config", beam_config);
        return this;
    }

    public double getValue() {
        return getDouble("value");
    }

    public Distance setValue(double value) {
        values.put("value", value);
        return this;
    }

}
