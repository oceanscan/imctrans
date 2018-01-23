package pt.lsts.imc;

public class FormState extends IMCMessage {
    public static final int ID_STATIC = 510;


    public enum POS_SIM_MON {
        OK(0),
        WRN(1),
        LIM(2);

        protected long value;

        public long value() {
            return value;
        }

        POS_SIM_MON(long value) {
            this.value = value;
        }
    }

    public enum COMM_MON {
        OK(0),
        TIMEOUT(1);

        protected long value;

        public long value() {
            return value;
        }

        COMM_MON(long value) {
            this.value = value;
        }
    }

    public enum CONVERG_MON {
        OK(0),
        TIMEOUT(1);

        protected long value;

        public long value() {
            return value;
        }

        CONVERG_MON(long value) {
            this.value = value;
        }
    }

    public FormState() {
        super(ID_STATIC);
    }

    public FormState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormState create(Object... values) {
        FormState m = new FormState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormState clone(IMCMessage msg) throws Exception {
        FormState m = new FormState();
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

    public double getPossimerr() {
        return getDouble("PosSimErr");
    }

    public FormState setPossimerr(double PosSimErr) {
        values.put("PosSimErr", PosSimErr);
        return this;
    }

    public double getConverg() {
        return getDouble("Converg");
    }

    public FormState setConverg(double Converg) {
        values.put("Converg", Converg);
        return this;
    }

    public double getTurbulence() {
        return getDouble("Turbulence");
    }

    public FormState setTurbulence(double Turbulence) {
        values.put("Turbulence", Turbulence);
        return this;
    }

    public POS_SIM_MON getPossimmon() {
        try {
            POS_SIM_MON o = POS_SIM_MON.valueOf(getMessageType().getFieldPossibleValues("PosSimMon").get(getLong("PosSimMon")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getPossimmonStr() {
        return getString("PosSimMon");
    }

    public short getPossimmonVal() {
        return getShort("PosSimMon");
    }

    public FormState setPossimmonStr(String PosSimMon) {
        setValue("PosSimMon", PosSimMon);
        return this;
    }

    public FormState setPossimmonVal(POS_SIM_MON PosSimMon) {
        setValue("PosSimMon", PosSimMon);
        return this;
    }

    public FormState setPossimmon(POS_SIM_MON PosSimMon) {
        values.put("PosSimMon", PosSimMon.value());
        return this;
    }

    public COMM_MON getCommmon() {
        try {
            COMM_MON o = COMM_MON.valueOf(getMessageType().getFieldPossibleValues("CommMon").get(getLong("CommMon")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getCommmonStr() {
        return getString("CommMon");
    }

    public short getCommmonVal() {
        return getShort("CommMon");
    }

    public FormState setCommmonStr(String CommMon) {
        setValue("CommMon", CommMon);
        return this;
    }

    public FormState setCommmonVal(COMM_MON CommMon) {
        setValue("CommMon", CommMon);
        return this;
    }

    public FormState setCommmon(COMM_MON CommMon) {
        values.put("CommMon", CommMon.value());
        return this;
    }

    public CONVERG_MON getConvergmon() {
        try {
            CONVERG_MON o = CONVERG_MON.valueOf(getMessageType().getFieldPossibleValues("ConvergMon").get(getLong("ConvergMon")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getConvergmonStr() {
        return getString("ConvergMon");
    }

    public short getConvergmonVal() {
        return getShort("ConvergMon");
    }

    public FormState setConvergmonStr(String ConvergMon) {
        setValue("ConvergMon", ConvergMon);
        return this;
    }

    public FormState setConvergmonVal(CONVERG_MON ConvergMon) {
        setValue("ConvergMon", ConvergMon);
        return this;
    }

    public FormState setConvergmon(CONVERG_MON ConvergMon) {
        values.put("ConvergMon", ConvergMon.value());
        return this;
    }

}
