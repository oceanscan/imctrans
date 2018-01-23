package pt.lsts.imc;

public class Sample extends Maneuver {
    public static final int ID_STATIC = 489;


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

    public enum SYRINGE0 {
        FALSE(0),
        TRUE(1);

        protected long value;

        public long value() {
            return value;
        }

        SYRINGE0(long value) {
            this.value = value;
        }
    }

    public enum SYRINGE1 {
        FALSE(0),
        TRUE(1);

        protected long value;

        public long value() {
            return value;
        }

        SYRINGE1(long value) {
            this.value = value;
        }
    }

    public enum SYRINGE2 {
        FALSE(0),
        TRUE(1);

        protected long value;

        public long value() {
            return value;
        }

        SYRINGE2(long value) {
            this.value = value;
        }
    }

    public Sample() {
        super(ID_STATIC);
    }

    public Sample(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Sample(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Sample create(Object... values) {
        Sample m = new Sample();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Sample clone(IMCMessage msg) throws Exception {
        Sample m = new Sample();
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

    public Sample setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Sample setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Sample setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public Sample setZ(double z) {
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

    public Sample setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Sample setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Sample setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public Sample setSpeed(double speed) {
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

    public Sample setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Sample setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Sample setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public SYRINGE0 getSyringe0() {
        try {
            SYRINGE0 o = SYRINGE0.valueOf(getMessageType().getFieldPossibleValues("syringe0").get(getLong("syringe0")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSyringe0Str() {
        return getString("syringe0");
    }

    public short getSyringe0Val() {
        return getShort("syringe0");
    }

    public Sample setSyringe0Str(String syringe0) {
        setValue("syringe0", syringe0);
        return this;
    }

    public Sample setSyringe0Val(SYRINGE0 syringe0) {
        setValue("syringe0", syringe0);
        return this;
    }

    public Sample setSyringe0(SYRINGE0 syringe0) {
        values.put("syringe0", syringe0.value());
        return this;
    }

    public SYRINGE1 getSyringe1() {
        try {
            SYRINGE1 o = SYRINGE1.valueOf(getMessageType().getFieldPossibleValues("syringe1").get(getLong("syringe1")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSyringe1Str() {
        return getString("syringe1");
    }

    public short getSyringe1Val() {
        return getShort("syringe1");
    }

    public Sample setSyringe1Str(String syringe1) {
        setValue("syringe1", syringe1);
        return this;
    }

    public Sample setSyringe1Val(SYRINGE1 syringe1) {
        setValue("syringe1", syringe1);
        return this;
    }

    public Sample setSyringe1(SYRINGE1 syringe1) {
        values.put("syringe1", syringe1.value());
        return this;
    }

    public SYRINGE2 getSyringe2() {
        try {
            SYRINGE2 o = SYRINGE2.valueOf(getMessageType().getFieldPossibleValues("syringe2").get(getLong("syringe2")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSyringe2Str() {
        return getString("syringe2");
    }

    public short getSyringe2Val() {
        return getShort("syringe2");
    }

    public Sample setSyringe2Str(String syringe2) {
        setValue("syringe2", syringe2);
        return this;
    }

    public Sample setSyringe2Val(SYRINGE2 syringe2) {
        setValue("syringe2", syringe2);
        return this;
    }

    public Sample setSyringe2(SYRINGE2 syringe2) {
        values.put("syringe2", syringe2.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Sample setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public Sample setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
