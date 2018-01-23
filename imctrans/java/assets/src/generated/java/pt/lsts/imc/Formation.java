package pt.lsts.imc;

public class Formation extends IMCMessage {
    public static final int ID_STATIC = 484;


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
        STOP(1),
        READY(2),
        EXECUTING(3),
        FAILURE(4);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public enum REFERENCE_FRAME {
        EARTH_FIXED(0),
        PATH_FIXED(1),
        PATH_CURVED(2);

        protected long value;

        public long value() {
            return value;
        }

        REFERENCE_FRAME(long value) {
            this.value = value;
        }
    }

    public Formation() {
        super(ID_STATIC);
    }

    public Formation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Formation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Formation create(Object... values) {
        Formation m = new Formation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Formation clone(IMCMessage msg) throws Exception {
        Formation m = new Formation();
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

    public String getFormationName() {
        return getString("formation_name");
    }

    public Formation setFormationName(String formation_name) {
        values.put("formation_name", formation_name);
        return this;
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

    public Formation setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public Formation setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public Formation setType(TYPE type) {
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

    public Formation setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public Formation setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public Formation setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getGroupName() {
        return getString("group_name");
    }

    public Formation setGroupName(String group_name) {
        values.put("group_name", group_name);
        return this;
    }

    public String getPlanId() {
        return getString("plan_id");
    }

    public Formation setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public String getDescription() {
        return getString("description");
    }

    public Formation setDescription(String description) {
        values.put("description", description);
        return this;
    }

    public REFERENCE_FRAME getReferenceFrame() {
        try {
            REFERENCE_FRAME o = REFERENCE_FRAME.valueOf(getMessageType().getFieldPossibleValues("reference_frame").get(getLong("reference_frame")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getReferenceFrameStr() {
        return getString("reference_frame");
    }

    public short getReferenceFrameVal() {
        return getShort("reference_frame");
    }

    public Formation setReferenceFrameStr(String reference_frame) {
        setValue("reference_frame", reference_frame);
        return this;
    }

    public Formation setReferenceFrameVal(REFERENCE_FRAME reference_frame) {
        setValue("reference_frame", reference_frame);
        return this;
    }

    public Formation setReferenceFrame(REFERENCE_FRAME reference_frame) {
        values.put("reference_frame", reference_frame.value());
        return this;
    }

    public java.util.Vector<VehicleFormationParticipant> getParticipants() {
        return getMessageListOrNull("participants", VehicleFormationParticipant.class);
    }

    public Formation setParticipants(java.util.Collection<VehicleFormationParticipant> participants) {
        values.put("participants", participants);
        return this;
    }

    public double getLeaderBankLim() {
        return getDouble("leader_bank_lim");
    }

    public Formation setLeaderBankLim(double leader_bank_lim) {
        values.put("leader_bank_lim", leader_bank_lim);
        return this;
    }

    public double getLeaderSpeedMin() {
        return getDouble("leader_speed_min");
    }

    public Formation setLeaderSpeedMin(double leader_speed_min) {
        values.put("leader_speed_min", leader_speed_min);
        return this;
    }

    public double getLeaderSpeedMax() {
        return getDouble("leader_speed_max");
    }

    public Formation setLeaderSpeedMax(double leader_speed_max) {
        values.put("leader_speed_max", leader_speed_max);
        return this;
    }

    public double getLeaderAltMin() {
        return getDouble("leader_alt_min");
    }

    public Formation setLeaderAltMin(double leader_alt_min) {
        values.put("leader_alt_min", leader_alt_min);
        return this;
    }

    public double getLeaderAltMax() {
        return getDouble("leader_alt_max");
    }

    public Formation setLeaderAltMax(double leader_alt_max) {
        values.put("leader_alt_max", leader_alt_max);
        return this;
    }

    public double getPosSimErrLim() {
        return getDouble("pos_sim_err_lim");
    }

    public Formation setPosSimErrLim(double pos_sim_err_lim) {
        values.put("pos_sim_err_lim", pos_sim_err_lim);
        return this;
    }

    public double getPosSimErrWrn() {
        return getDouble("pos_sim_err_wrn");
    }

    public Formation setPosSimErrWrn(double pos_sim_err_wrn) {
        values.put("pos_sim_err_wrn", pos_sim_err_wrn);
        return this;
    }

    public int getPosSimErrTimeout() {
        return getInteger("pos_sim_err_timeout");
    }

    public Formation setPosSimErrTimeout(int pos_sim_err_timeout) {
        values.put("pos_sim_err_timeout", pos_sim_err_timeout);
        return this;
    }

    public double getConvergMax() {
        return getDouble("converg_max");
    }

    public Formation setConvergMax(double converg_max) {
        values.put("converg_max", converg_max);
        return this;
    }

    public int getConvergTimeout() {
        return getInteger("converg_timeout");
    }

    public Formation setConvergTimeout(int converg_timeout) {
        values.put("converg_timeout", converg_timeout);
        return this;
    }

    public int getCommsTimeout() {
        return getInteger("comms_timeout");
    }

    public Formation setCommsTimeout(int comms_timeout) {
        values.put("comms_timeout", comms_timeout);
        return this;
    }

    public double getTurbLim() {
        return getDouble("turb_lim");
    }

    public Formation setTurbLim(double turb_lim) {
        values.put("turb_lim", turb_lim);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Formation setCustom(java.util.LinkedHashMap<String, ?> custom) {
        return setCustom(custom);
    }

    public Formation setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
