package pt.lsts.imc;

public class CommsRelay extends Maneuver {
    public static final int ID_STATIC = 472;


    public enum SPEED_UNITS {
        METERS_PS(0),
        RPM(1),
        PERCENTAGE(2);

        protected long value;

        public long value() {
            return value;
        }

        SPEED_UNITS(long value) {
            this.value = value;
        }
    }

    public CommsRelay() {
        super(ID_STATIC);
    }

    public CommsRelay(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CommsRelay(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CommsRelay create(Object... values) {
        CommsRelay m = new CommsRelay();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CommsRelay clone(IMCMessage msg) throws Exception {
        CommsRelay m = new CommsRelay();
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

    public double getLat() {
        return getDouble("lat");
    }

    public CommsRelay setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public CommsRelay setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public CommsRelay setSpeed(double speed) {
        values.put("speed", speed);
        return this;
    }

    public SPEED_UNITS getSpeedUnits() {
        try {
            SPEED_UNITS o = SPEED_UNITS.valueOf(getMessageType().getFieldPossibleValues("speed_units").get(getLong("speed_units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSpeedUnitsStr() {
        return getString("speed_units");
    }

    public short getSpeedUnitsVal() {
        return getShort("speed_units");
    }

    public CommsRelay setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public CommsRelay setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public CommsRelay setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public int getDuration() {
        return getInteger("duration");
    }

    public CommsRelay setDuration(int duration) {
        values.put("duration", duration);
        return this;
    }

    public int getSysA() {
        return getInteger("sys_a");
    }

    public CommsRelay setSysA(int sys_a) {
        values.put("sys_a", sys_a);
        return this;
    }

    public int getSysB() {
        return getInteger("sys_b");
    }

    public CommsRelay setSysB(int sys_b) {
        values.put("sys_b", sys_b);
        return this;
    }

    public double getMoveThreshold() {
        return getDouble("move_threshold");
    }

    public CommsRelay setMoveThreshold(double move_threshold) {
        values.put("move_threshold", move_threshold);
        return this;
    }

}
