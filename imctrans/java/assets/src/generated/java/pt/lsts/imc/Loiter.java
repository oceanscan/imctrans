package pt.lsts.imc;

public class Loiter extends Maneuver {
    public static final int ID_STATIC = 453;


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

    public enum TYPE {
        DEFAULT(0),
        CIRCULAR(1),
        RACETRACK(2),
        EIGHT(3),
        HOVER(4);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public enum DIRECTION {
        VDEP(0),
        CLOCKW(1),
        CCLOCKW(2),
        IWINDCURR(3);

        protected long value;

        public long value() {
            return value;
        }

        DIRECTION(long value) {
            this.value = value;
        }
    }

    public Loiter() {
        super(ID_STATIC);
    }

    public Loiter(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Loiter(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Loiter create(Object... values) {
        Loiter m = new Loiter();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Loiter clone(IMCMessage msg) throws Exception {
        Loiter m = new Loiter();
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

    public Loiter setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Loiter setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Loiter setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public Loiter setZ(double z) {
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

    public Loiter setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Loiter setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Loiter setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public int getDuration() {
        return getInteger("duration");
    }

    public Loiter setDuration(int duration) {
        values.put("duration", duration);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public Loiter setSpeed(double speed) {
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

    public Loiter setSpeedUnitsStr(String speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Loiter setSpeedUnitsVal(SPEED_UNITS speed_units) {
        setValue("speed_units", speed_units);
        return this;
    }

    public Loiter setSpeedUnits(SPEED_UNITS speed_units) {
        values.put("speed_units", speed_units.value());
        return this;
    }

    public TYPE getType() {
        try {
            TYPE o = TYPE.valueOf(getMessageType().getFieldPossibleValues("type").get(getLong("type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTypeStr() {
        return getString("type");
    }

    public short getTypeVal() {
        return getShort("type");
    }

    public Loiter setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public Loiter setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public Loiter setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public double getRadius() {
        return getDouble("radius");
    }

    public Loiter setRadius(double radius) {
        values.put("radius", radius);
        return this;
    }

    public double getLength() {
        return getDouble("length");
    }

    public Loiter setLength(double length) {
        values.put("length", length);
        return this;
    }

    public double getBearing() {
        return getDouble("bearing");
    }

    public Loiter setBearing(double bearing) {
        values.put("bearing", bearing);
        return this;
    }

    public DIRECTION getDirection() {
        try {
            DIRECTION o = DIRECTION.valueOf(getMessageType().getFieldPossibleValues("direction").get(getLong("direction")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getDirectionStr() {
        return getString("direction");
    }

    public short getDirectionVal() {
        return getShort("direction");
    }

    public Loiter setDirectionStr(String direction) {
        setValue("direction", direction);
        return this;
    }

    public Loiter setDirectionVal(DIRECTION direction) {
        setValue("direction", direction);
        return this;
    }

    public Loiter setDirection(DIRECTION direction) {
        values.put("direction", direction.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Loiter setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public Loiter setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
