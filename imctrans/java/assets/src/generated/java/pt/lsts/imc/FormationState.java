package pt.lsts.imc;

public class FormationState extends IMCMessage {
    public static final int ID_STATIC = 512;


    public enum TYPE {
        REQUEST(0),
        REPORT(1);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public enum OP {
        START(0),
        STOP(1);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

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

    public FormationState() {
        super(ID_STATIC);
    }

    public FormationState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormationState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormationState create(Object... values) {
        FormationState m = new FormationState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormationState clone(IMCMessage msg) throws Exception {
        FormationState m = new FormationState();
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

    public FormationState setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public FormationState setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public FormationState setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public OP getOp() {
        try {
            OP o = OP.valueOf(getMessageType().getFieldPossibleValues("op").get(getLong("op")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getOpStr() {
        return getString("op");
    }

    public short getOpVal() {
        return getShort("op");
    }

    public FormationState setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public FormationState setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public FormationState setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public double getPossimerr() {
        return getDouble("PosSimErr");
    }

    public FormationState setPossimerr(double PosSimErr) {
        values.put("PosSimErr", PosSimErr);
        return this;
    }

    public double getConverg() {
        return getDouble("Converg");
    }

    public FormationState setConverg(double Converg) {
        values.put("Converg", Converg);
        return this;
    }

    public double getTurbulence() {
        return getDouble("Turbulence");
    }

    public FormationState setTurbulence(double Turbulence) {
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

    public FormationState setPossimmonStr(String PosSimMon) {
        setValue("PosSimMon", PosSimMon);
        return this;
    }

    public FormationState setPossimmonVal(POS_SIM_MON PosSimMon) {
        setValue("PosSimMon", PosSimMon);
        return this;
    }

    public FormationState setPossimmon(POS_SIM_MON PosSimMon) {
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

    public FormationState setCommmonStr(String CommMon) {
        setValue("CommMon", CommMon);
        return this;
    }

    public FormationState setCommmonVal(COMM_MON CommMon) {
        setValue("CommMon", CommMon);
        return this;
    }

    public FormationState setCommmon(COMM_MON CommMon) {
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

    public FormationState setConvergmonStr(String ConvergMon) {
        setValue("ConvergMon", ConvergMon);
        return this;
    }

    public FormationState setConvergmonVal(CONVERG_MON ConvergMon) {
        setValue("ConvergMon", ConvergMon);
        return this;
    }

    public FormationState setConvergmon(CONVERG_MON ConvergMon) {
        values.put("ConvergMon", ConvergMon.value());
        return this;
    }

}
