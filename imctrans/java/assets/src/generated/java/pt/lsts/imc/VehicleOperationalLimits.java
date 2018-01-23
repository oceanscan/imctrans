package pt.lsts.imc;

public class VehicleOperationalLimits extends IMCMessage {
    public static final int ID_STATIC = 16;


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

    public VehicleOperationalLimits() {
        super(ID_STATIC);
    }

    public VehicleOperationalLimits(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VehicleOperationalLimits(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VehicleOperationalLimits create(Object... values) {
        VehicleOperationalLimits m = new VehicleOperationalLimits();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VehicleOperationalLimits clone(IMCMessage msg) throws Exception {
        VehicleOperationalLimits m = new VehicleOperationalLimits();
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

    public VehicleOperationalLimits setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public VehicleOperationalLimits setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public VehicleOperationalLimits setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getSpeedMin() {
        return getDouble("speed_min");
    }

    public VehicleOperationalLimits setSpeedMin(double speed_min) {
        values.put("speed_min", speed_min);
        return this;
    }

    public double getSpeedMax() {
        return getDouble("speed_max");
    }

    public VehicleOperationalLimits setSpeedMax(double speed_max) {
        values.put("speed_max", speed_max);
        return this;
    }

    public double getLongAccel() {
        return getDouble("long_accel");
    }

    public VehicleOperationalLimits setLongAccel(double long_accel) {
        values.put("long_accel", long_accel);
        return this;
    }

    public double getAltMaxMsl() {
        return getDouble("alt_max_msl");
    }

    public VehicleOperationalLimits setAltMaxMsl(double alt_max_msl) {
        values.put("alt_max_msl", alt_max_msl);
        return this;
    }

    public double getDiveFractionMax() {
        return getDouble("dive_fraction_max");
    }

    public VehicleOperationalLimits setDiveFractionMax(double dive_fraction_max) {
        values.put("dive_fraction_max", dive_fraction_max);
        return this;
    }

    public double getClimbFractionMax() {
        return getDouble("climb_fraction_max");
    }

    public VehicleOperationalLimits setClimbFractionMax(double climb_fraction_max) {
        values.put("climb_fraction_max", climb_fraction_max);
        return this;
    }

    public double getBankMax() {
        return getDouble("bank_max");
    }

    public VehicleOperationalLimits setBankMax(double bank_max) {
        values.put("bank_max", bank_max);
        return this;
    }

    public double getPMax() {
        return getDouble("p_max");
    }

    public VehicleOperationalLimits setPMax(double p_max) {
        values.put("p_max", p_max);
        return this;
    }

    public double getPitchMin() {
        return getDouble("pitch_min");
    }

    public VehicleOperationalLimits setPitchMin(double pitch_min) {
        values.put("pitch_min", pitch_min);
        return this;
    }

    public double getPitchMax() {
        return getDouble("pitch_max");
    }

    public VehicleOperationalLimits setPitchMax(double pitch_max) {
        values.put("pitch_max", pitch_max);
        return this;
    }

    public double getQMax() {
        return getDouble("q_max");
    }

    public VehicleOperationalLimits setQMax(double q_max) {
        values.put("q_max", q_max);
        return this;
    }

    public double getGMin() {
        return getDouble("g_min");
    }

    public VehicleOperationalLimits setGMin(double g_min) {
        values.put("g_min", g_min);
        return this;
    }

    public double getGMax() {
        return getDouble("g_max");
    }

    public VehicleOperationalLimits setGMax(double g_max) {
        values.put("g_max", g_max);
        return this;
    }

    public double getGLatMax() {
        return getDouble("g_lat_max");
    }

    public VehicleOperationalLimits setGLatMax(double g_lat_max) {
        values.put("g_lat_max", g_lat_max);
        return this;
    }

    public double getRpmMin() {
        return getDouble("rpm_min");
    }

    public VehicleOperationalLimits setRpmMin(double rpm_min) {
        values.put("rpm_min", rpm_min);
        return this;
    }

    public double getRpmMax() {
        return getDouble("rpm_max");
    }

    public VehicleOperationalLimits setRpmMax(double rpm_max) {
        values.put("rpm_max", rpm_max);
        return this;
    }

    public double getRpmRateMax() {
        return getDouble("rpm_rate_max");
    }

    public VehicleOperationalLimits setRpmRateMax(double rpm_rate_max) {
        values.put("rpm_rate_max", rpm_rate_max);
        return this;
    }

}
