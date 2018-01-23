package pt.lsts.imc;

public class UsblFixExtended extends IMCMessage {
    public static final int ID_STATIC = 900;


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

    public UsblFixExtended() {
        super(ID_STATIC);
    }

    public UsblFixExtended(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblFixExtended(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblFixExtended create(Object... values) {
        UsblFixExtended m = new UsblFixExtended();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblFixExtended clone(IMCMessage msg) throws Exception {
        UsblFixExtended m = new UsblFixExtended();
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

    public String getTarget() {
        return getString("target");
    }

    public UsblFixExtended setTarget(String target) {
        values.put("target", target);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public UsblFixExtended setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public UsblFixExtended setLon(double lon) {
        values.put("lon", lon);
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

    public UsblFixExtended setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public UsblFixExtended setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public UsblFixExtended setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public UsblFixExtended setZ(double z) {
        values.put("z", z);
        return this;
    }

    public double getAccuracy() {
        return getDouble("accuracy");
    }

    public UsblFixExtended setAccuracy(double accuracy) {
        values.put("accuracy", accuracy);
        return this;
    }

}
