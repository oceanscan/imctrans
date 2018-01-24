package pt.lsts.imc;

public class FormationParameters extends IMCMessage {
    public static final int ID_STATIC = 476;


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

    public FormationParameters() {
        super(ID_STATIC);
    }

    public FormationParameters(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormationParameters(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormationParameters create(Object... values) {
        FormationParameters m = new FormationParameters();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormationParameters clone(IMCMessage msg) throws Exception {
        FormationParameters m = new FormationParameters();
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

    public FormationParameters setFormationName(String formation_name) {
        values.put("formation_name", formation_name);
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

    public FormationParameters setReferenceFrameStr(String reference_frame) {
        setValue("reference_frame", reference_frame);
        return this;
    }

    public FormationParameters setReferenceFrameVal(REFERENCE_FRAME reference_frame) {
        setValue("reference_frame", reference_frame);
        return this;
    }

    public FormationParameters setReferenceFrame(REFERENCE_FRAME reference_frame) {
        values.put("reference_frame", reference_frame.value());
        return this;
    }

    public java.util.Vector<VehicleFormationParticipant> getParticipants() {
        return getMessageListOrNull("participants", VehicleFormationParticipant.class);
    }

    public FormationParameters setParticipants(java.util.Collection<VehicleFormationParticipant> participants) {
        values.put("participants", participants);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public FormationParameters setCustom(java.util.LinkedHashMap<String, ?> custom) {
        values.put("custom", encodeTupleList(custom));
        return this;
    }

    public FormationParameters setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
