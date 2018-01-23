package pt.lsts.imc;

public class DesiredZ extends ControlCommand {
    public static final int ID_STATIC = 401;


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

    public DesiredZ() {
        super(ID_STATIC);
    }

    public DesiredZ(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DesiredZ(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DesiredZ create(Object... values) {
        DesiredZ m = new DesiredZ();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DesiredZ clone(IMCMessage msg) throws Exception {
        DesiredZ m = new DesiredZ();
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

    public DesiredZ setValue(double value) {
        values.put("value", value);
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

    public DesiredZ setZUnitsStr(String z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public DesiredZ setZUnitsVal(Z_UNITS z_units) {
        setValue("z_units", z_units);
        return this;
    }

    public DesiredZ setZUnits(Z_UNITS z_units) {
        values.put("z_units", z_units.value());
        return this;
    }

}
