package pt.lsts.imc;

public class Drop extends Maneuver {
    public static final int ID_STATIC = 486;


    public enum Z_UNITS {
        NONE(0),
        DEPTH(1),
        ALTITUDE(2),
        HEIGHT(3);

        protected long value;

        public long value() {
            return value;
        }

        Z_UNITS(long value) {
            this.value = value;
        }
    }

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

    public Drop() {
        super(ID_STATIC);
    }

    public Drop(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Drop(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Drop create(Object... values) {
        Drop m = new Drop();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Drop clone(IMCMessage msg) throws Exception {
        Drop m = new Drop();
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

    public int getTimeout() {
        return getInteger("timeout");
    }

    public Drop setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Drop setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Drop setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public Drop setZ(double z) {
        values.put("z", z);
        return this;
    }

    public Z_UNITS getZUnits() {
        try {
            Z_UNITS o = Z_UNITS.valueOf(getMessageType().getFieldPossibleValues("z_units").get(getLong("z_units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getZUnitsStr() {
        return getString("z_units");
    }

    public short getZUnitsVal() {
        return getShort("z_units");
    }

    public Drop setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Drop setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Drop setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public Drop setSpeed(double speed) {
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

    public Drop setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Drop setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Drop setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Drop setCustom(java.util.LinkedHashMap<String, ?> custom) {
        values.put("custom", encodeTupleList(custom));
        return this;
    }

    public Drop setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}