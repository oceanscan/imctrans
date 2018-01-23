package pt.lsts.imc;

public class DevCalibrationState extends IMCMessage {
    public static final int ID_STATIC = 13;

public static final short DCS_PREVIOUS_NOT_SUPPORTED = 0x01;
public static final short DCS_NEXT_NOT_SUPPORTED = 0x02;
public static final short DCS_WAITING_CONTROL = 0x04;
public static final short DCS_ERROR = 0x08;
public static final short DCS_COMPLETED = 0x10;

    public DevCalibrationState() {
        super(ID_STATIC);
    }

    public DevCalibrationState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DevCalibrationState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DevCalibrationState create(Object... values) {
        DevCalibrationState m = new DevCalibrationState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DevCalibrationState clone(IMCMessage msg) throws Exception {
        DevCalibrationState m = new DevCalibrationState();
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

    public short getTotalSteps() {
        return getShort("total_steps");
    }

    public DevCalibrationState setTotalSteps(short total_steps) {
        values.put("total_steps", total_steps);
        return this;
    }

    public short getStepNumber() {
        return getShort("step_number");
    }

    public DevCalibrationState setStepNumber(short step_number) {
        values.put("step_number", step_number);
        return this;
    }

    public String getStep() {
        return getString("step");
    }

    public DevCalibrationState setStep(String step) {
        values.put("step", step);
        return this;
    }

    public short getFlags() {
        return getShort("flags");
    }

    public DevCalibrationState setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

}
