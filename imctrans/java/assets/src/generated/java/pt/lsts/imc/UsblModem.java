package pt.lsts.imc;

public class UsblModem extends IMCMessage {
    public static final int ID_STATIC = 901;


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

    public UsblModem() {
        super(ID_STATIC);
    }

    public UsblModem(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UsblModem(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UsblModem create(Object... values) {
        UsblModem m = new UsblModem();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UsblModem clone(IMCMessage msg) throws Exception {
        UsblModem m = new UsblModem();
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

    public String getName() {
        return getString("name");
    }

    public UsblModem setName(String name) {
        values.put("name", name);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public UsblModem setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public UsblModem setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getZ() {
        return getDouble("z");
    }

    public UsblModem setZ(double z) {
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

    public UsblModem setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public UsblModem setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public UsblModem setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

}
