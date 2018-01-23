package pt.lsts.imc;

public class Tachograph extends IMCMessage {
    public static final int ID_STATIC = 905;


    public Tachograph() {
        super(ID_STATIC);
    }

    public Tachograph(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Tachograph(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Tachograph create(Object... values) {
        Tachograph m = new Tachograph();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Tachograph clone(IMCMessage msg) throws Exception {
        Tachograph m = new Tachograph();
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

    public double getTimestampLastService() {
        return getDouble("timestamp_last_service");
    }

    public Tachograph setTimestampLastService(double timestamp_last_service) {
        values.put("timestamp_last_service", timestamp_last_service);
        return this;
    }

    public double getTimeNextService() {
        return getDouble("time_next_service");
    }

    public Tachograph setTimeNextService(double time_next_service) {
        values.put("time_next_service", time_next_service);
        return this;
    }

    public double getTimeMotorNextService() {
        return getDouble("time_motor_next_service");
    }

    public Tachograph setTimeMotorNextService(double time_motor_next_service) {
        values.put("time_motor_next_service", time_motor_next_service);
        return this;
    }

    public double getTimeIdleGround() {
        return getDouble("time_idle_ground");
    }

    public Tachograph setTimeIdleGround(double time_idle_ground) {
        values.put("time_idle_ground", time_idle_ground);
        return this;
    }

    public double getTimeIdleAir() {
        return getDouble("time_idle_air");
    }

    public Tachograph setTimeIdleAir(double time_idle_air) {
        values.put("time_idle_air", time_idle_air);
        return this;
    }

    public double getTimeIdleWater() {
        return getDouble("time_idle_water");
    }

    public Tachograph setTimeIdleWater(double time_idle_water) {
        values.put("time_idle_water", time_idle_water);
        return this;
    }

    public double getTimeIdleUnderwater() {
        return getDouble("time_idle_underwater");
    }

    public Tachograph setTimeIdleUnderwater(double time_idle_underwater) {
        values.put("time_idle_underwater", time_idle_underwater);
        return this;
    }

    public double getTimeIdleUnknown() {
        return getDouble("time_idle_unknown");
    }

    public Tachograph setTimeIdleUnknown(double time_idle_unknown) {
        values.put("time_idle_unknown", time_idle_unknown);
        return this;
    }

    public double getTimeMotorGround() {
        return getDouble("time_motor_ground");
    }

    public Tachograph setTimeMotorGround(double time_motor_ground) {
        values.put("time_motor_ground", time_motor_ground);
        return this;
    }

    public double getTimeMotorAir() {
        return getDouble("time_motor_air");
    }

    public Tachograph setTimeMotorAir(double time_motor_air) {
        values.put("time_motor_air", time_motor_air);
        return this;
    }

    public double getTimeMotorWater() {
        return getDouble("time_motor_water");
    }

    public Tachograph setTimeMotorWater(double time_motor_water) {
        values.put("time_motor_water", time_motor_water);
        return this;
    }

    public double getTimeMotorUnderwater() {
        return getDouble("time_motor_underwater");
    }

    public Tachograph setTimeMotorUnderwater(double time_motor_underwater) {
        values.put("time_motor_underwater", time_motor_underwater);
        return this;
    }

    public double getTimeMotorUnknown() {
        return getDouble("time_motor_unknown");
    }

    public Tachograph setTimeMotorUnknown(double time_motor_unknown) {
        values.put("time_motor_unknown", time_motor_unknown);
        return this;
    }

    public short getRpmMin() {
        return getShort("rpm_min");
    }

    public Tachograph setRpmMin(short rpm_min) {
        values.put("rpm_min", rpm_min);
        return this;
    }

    public short getRpmMax() {
        return getShort("rpm_max");
    }

    public Tachograph setRpmMax(short rpm_max) {
        values.put("rpm_max", rpm_max);
        return this;
    }

    public double getDepthMax() {
        return getDouble("depth_max");
    }

    public Tachograph setDepthMax(double depth_max) {
        values.put("depth_max", depth_max);
        return this;
    }

}
