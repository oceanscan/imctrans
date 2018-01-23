package pt.lsts.imc;

public class DesiredSpeed extends ControlCommand {
    public static final int ID_STATIC = 402;


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

    public DesiredSpeed() {
        super(ID_STATIC);
    }

    public DesiredSpeed(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredSpeed(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredSpeed create(Object... values) {
        DesiredSpeed m = new DesiredSpeed();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredSpeed clone(IMCMessage msg) throws Exception {
        DesiredSpeed m = new DesiredSpeed();
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

    public double getValue() {
        return getDouble("value");
    }

    public DesiredSpeed setValue(double value) {
        values.put("value", value);
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

    public DesiredSpeed setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public DesiredSpeed setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public DesiredSpeed setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

}
