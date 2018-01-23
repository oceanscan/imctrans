package pt.lsts.imc;

public class UsblFix extends IMCMessage {
    public static final int ID_STATIC = 892;


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

    public UsblFix() {
        super(ID_STATIC);
    }

    public UsblFix(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblFix(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblFix create(Object... values) {
        UsblFix m = new UsblFix();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblFix clone(IMCMessage msg) throws Exception {
        UsblFix m = new UsblFix();
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

    public int getTarget() {
        return getInteger("target");
    }

    public UsblFix setTarget(int target) {
        values.put("target", target);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public UsblFix setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public UsblFix setLon(double lon) {
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

    public UsblFix setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public UsblFix setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public UsblFix setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public UsblFix setZ(double z) {
        values.put("z", z);
        return this;
    }

}
