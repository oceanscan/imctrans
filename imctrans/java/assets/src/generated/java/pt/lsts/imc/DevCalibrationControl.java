package pt.lsts.imc;

public class DevCalibrationControl extends IMCMessage {
    public static final int ID_STATIC = 12;


    public enum OP {
        START(0),
        STOP(1),
        STEP_NEXT(2),
        STEP_PREVIOUS(3);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public DevCalibrationControl() {
        super(ID_STATIC);
    }

    public DevCalibrationControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DevCalibrationControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DevCalibrationControl create(Object... values) {
        DevCalibrationControl m = new DevCalibrationControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DevCalibrationControl clone(IMCMessage msg) throws Exception {
        DevCalibrationControl m = new DevCalibrationControl();
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

    public DevCalibrationControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public DevCalibrationControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public DevCalibrationControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

}
