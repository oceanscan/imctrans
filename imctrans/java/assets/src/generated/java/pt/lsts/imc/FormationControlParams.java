package pt.lsts.imc;

public class FormationControlParams extends IMCMessage {
    public static final int ID_STATIC = 822;


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

    public FormationControlParams() {
        super(ID_STATIC);
    }

    public FormationControlParams(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormationControlParams(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormationControlParams create(Object... values) {
        FormationControlParams m = new FormationControlParams();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormationControlParams clone(IMCMessage msg) throws Exception {
        FormationControlParams m = new FormationControlParams();
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

    public FormationControlParams setActionStr(String Action) {
        setValue("Action", Action);
        return this;
    }

    public FormationControlParams setActionVal(ACTION Action) {
        setValue("Action", Action);
        return this;
    }

    public FormationControlParams setAction(ACTION Action) {
        values.put("Action", Action.value());
        return this;
    }

    public double getLonGain() {
        return getDouble("lon_gain");
    }

    public FormationControlParams setLonGain(double lon_gain) {
        values.put("lon_gain", lon_gain);
        return this;
    }

    public double getLatGain() {
        return getDouble("lat_gain");
    }

    public FormationControlParams setLatGain(double lat_gain) {
        values.put("lat_gain", lat_gain);
        return this;
    }

    public double getBondThick() {
        return getDouble("bond_thick");
    }

    public FormationControlParams setBondThick(double bond_thick) {
        values.put("bond_thick", bond_thick);
        return this;
    }

    public double getLeadGain() {
        return getDouble("lead_gain");
    }

    public FormationControlParams setLeadGain(double lead_gain) {
        values.put("lead_gain", lead_gain);
        return this;
    }

    public double getDeconflGain() {
        return getDouble("deconfl_gain");
    }

    public FormationControlParams setDeconflGain(double deconfl_gain) {
        values.put("deconfl_gain", deconfl_gain);
        return this;
    }

    public double getAccelSwitchGain() {
        return getDouble("accel_switch_gain");
    }

    public FormationControlParams setAccelSwitchGain(double accel_switch_gain) {
        values.put("accel_switch_gain", accel_switch_gain);
        return this;
    }

    public double getSafeDist() {
        return getDouble("safe_dist");
    }

    public FormationControlParams setSafeDist(double safe_dist) {
        values.put("safe_dist", safe_dist);
        return this;
    }

    public double getDeconflictOffset() {
        return getDouble("deconflict_offset");
    }

    public FormationControlParams setDeconflictOffset(double deconflict_offset) {
        values.put("deconflict_offset", deconflict_offset);
        return this;
    }

    public double getAccelSafeMargin() {
        return getDouble("accel_safe_margin");
    }

    public FormationControlParams setAccelSafeMargin(double accel_safe_margin) {
        values.put("accel_safe_margin", accel_safe_margin);
        return this;
    }

    public double getAccelLimX() {
        return getDouble("accel_lim_x");
    }

    public FormationControlParams setAccelLimX(double accel_lim_x) {
        values.put("accel_lim_x", accel_lim_x);
        return this;
    }

}
