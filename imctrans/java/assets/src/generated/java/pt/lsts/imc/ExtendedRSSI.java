package pt.lsts.imc;

public class ExtendedRSSI extends IMCMessage {
    public static final int ID_STATIC = 183;


    public enum UNITS {
        dB(0),
        PERCENTAGE(1);

        protected long value;

        public long value() {
            return value;
        }

        UNITS(long value) {
            this.value = value;
        }
    }

    public ExtendedRSSI() {
        super(ID_STATIC);
    }

    public ExtendedRSSI(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ExtendedRSSI(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExtendedRSSI create(Object... values) {
        ExtendedRSSI m = new ExtendedRSSI();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ExtendedRSSI clone(IMCMessage msg) throws Exception {
        ExtendedRSSI m = new ExtendedRSSI();
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

    public ExtendedRSSI setValue(double value) {
        values.put("value", value);
        return this;
    }

    public UNITS getUnits() {
        try {
            UNITS o = UNITS.valueOf(getMessageType().getFieldPossibleValues("units").get(getLong("units")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getUnitsStr() {
        return getString("units");
    }

    public short getUnitsVal() {
        return getShort("units");
    }

    public ExtendedRSSI setUnitsStr(String units) {
        setValue("units", units);
        return this;
    }

    public ExtendedRSSI setUnitsVal(UNITS units) {
        setValue("units", units);
        return this;
    }

    public ExtendedRSSI setUnits(UNITS units) {
        values.put("units", units.value());
        return this;
    }

}
