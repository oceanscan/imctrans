package pt.lsts.imc;

public class FormCtrlParam extends IMCMessage {
    public static final int ID_STATIC = 820;


    public enum ACTION {
        REQ(0),
        SET(1),
        REP(2);

        protected long value;

        public long value() {
            return value;
        }

        ACTION(long value) {
            this.value = value;
        }
    }

    public FormCtrlParam() {
        super(ID_STATIC);
    }

    public FormCtrlParam(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormCtrlParam(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormCtrlParam create(Object... values) {
        FormCtrlParam m = new FormCtrlParam();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormCtrlParam clone(IMCMessage msg) throws Exception {
        FormCtrlParam m = new FormCtrlParam();
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

    public ACTION getAction() {
        try {
            ACTION o = ACTION.valueOf(getMessageType().getFieldPossibleValues("Action").get(getLong("Action")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getActionStr() {
        return getString("Action");
    }

    public short getActionVal() {
        return getShort("Action");
    }

    public FormCtrlParam setActionStr(String Action) {
        setValue("Action", Action);
        return this;
    }

    public FormCtrlParam setActionVal(ACTION Action) {
        setValue("Action", Action);
        return this;
    }

    public FormCtrlParam setAction(ACTION Action) {
        values.put("Action", Action.value());
        return this;
    }

    public double getLongain() {
        return getDouble("LonGain");
    }

    public FormCtrlParam setLongain(double LonGain) {
        values.put("LonGain", LonGain);
        return this;
    }

    public double getLatgain() {
        return getDouble("LatGain");
    }

    public FormCtrlParam setLatgain(double LatGain) {
        values.put("LatGain", LatGain);
        return this;
    }

    public long getBondthick() {
        return getLong("BondThick");
    }

    public FormCtrlParam setBondthick(long BondThick) {
        values.put("BondThick", BondThick);
        return this;
    }

    public double getLeadgain() {
        return getDouble("LeadGain");
    }

    public FormCtrlParam setLeadgain(double LeadGain) {
        values.put("LeadGain", LeadGain);
        return this;
    }

    public double getDeconflgain() {
        return getDouble("DeconflGain");
    }

    public FormCtrlParam setDeconflgain(double DeconflGain) {
        values.put("DeconflGain", DeconflGain);
        return this;
    }

}
