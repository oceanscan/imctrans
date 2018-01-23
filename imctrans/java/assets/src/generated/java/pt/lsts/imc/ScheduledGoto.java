package pt.lsts.imc;

public class ScheduledGoto extends Maneuver {
    public static final int ID_STATIC = 487;


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

    public enum TRAVEL_Z_UNITS {
        NONE(0),
        DEPTH(1),
        ALTITUDE(2),
        HEIGHT(3);

        protected long value;

        public long value() {
            return value;
        }

        TRAVEL_Z_UNITS(long value) {
            this.value = value;
        }
    }

    public enum DELAYED {
        RESUME(0),
        SKIP(1),
        FAIL(2);

        protected long value;

        public long value() {
            return value;
        }

        DELAYED(long value) {
            this.value = value;
        }
    }

    public ScheduledGoto() {
        super(ID_STATIC);
    }

    public ScheduledGoto(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ScheduledGoto(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ScheduledGoto create(Object... values) {
        ScheduledGoto m = new ScheduledGoto();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ScheduledGoto clone(IMCMessage msg) throws Exception {
        ScheduledGoto m = new ScheduledGoto();
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

    public double getArrivalTime() {
        return getDouble("arrival_time");
    }

    public ScheduledGoto setArrivalTime(double arrival_time) {
        values.put("arrival_time", arrival_time);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public ScheduledGoto setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public ScheduledGoto setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public ScheduledGoto setZ(double z) {
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

    public ScheduledGoto setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public ScheduledGoto setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public ScheduledGoto setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getTravelZ() {
        return getDouble("travel_z");
    }

    public ScheduledGoto setTravelZ(double travel_z) {
        values.put("travel_z", travel_z);
        return this;
    }

    public TRAVEL_Z_UNITS getTravelZUnits() {
        try {
            TRAVEL_Z_UNITS o = TRAVEL_Z_UNITS.valueOf(getMessageType().getFieldPossibleValues("travel_z_units").get(getLong("travel_z_units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTravelZUnitsStr() {
        return getString("travel_z_units");
    }

    public short getTravelZUnitsVal() {
        return getShort("travel_z_units");
    }

    public ScheduledGoto setTravelZUnitsStr(String travel_z_units) {
        setValue("travel_z_units", travel_z_units);
        return this;
    }

    public ScheduledGoto setTravelZUnitsVal(TRAVEL_Z_UNITS travel_z_units) {
        setValue("travel_z_units", travel_z_units);
        return this;
    }

    public ScheduledGoto setTravelZUnits(TRAVEL_Z_UNITS travel_z_units) {
        values.put("travel_z_units", travel_z_units.value());
        return this;
    }

    public DELAYED getDelayed() {
        try {
            DELAYED o = DELAYED.valueOf(getMessageType().getFieldPossibleValues("delayed").get(getLong("delayed")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getDelayedStr() {
        return getString("delayed");
    }

    public short getDelayedVal() {
        return getShort("delayed");
    }

    public ScheduledGoto setDelayedStr(String delayed) {
        setValue("delayed", delayed);
        return this;
    }

    public ScheduledGoto setDelayedVal(DELAYED delayed) {
        setValue("delayed", delayed);
        return this;
    }

    public ScheduledGoto setDelayed(DELAYED delayed) {
        values.put("delayed", delayed.value());
        return this;
    }

}
