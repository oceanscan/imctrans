package pt.lsts.imc;

public class Target extends IMCMessage {
    public static final int ID_STATIC = 800;


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

    public Target() {
        super(ID_STATIC);
    }

    public Target(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Target(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Target create(Object... values) {
        Target m = new Target();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Target clone(IMCMessage msg) throws Exception {
        Target m = new Target();
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

    public String getLabel() {
        return getString("label");
    }

    public Target setLabel(String label) {
        values.put("label", label);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Target setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Target setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public Target setZ(double z) {
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

    public Target setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Target setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public Target setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getCog() {
        return getDouble("cog");
    }

    public Target setCog(double cog) {
        values.put("cog", cog);
        return this;
    }

    public double getSog() {
        return getDouble("sog");
    }

    public Target setSog(double sog) {
        values.put("sog", sog);
        return this;
    }

}
