package pt.lsts.imc;

public class VehicleCommand extends IMCMessage {
    public static final int ID_STATIC = 501;


    public enum TYPE {
        REQUEST(0),
        SUCCESS(1),
        IN_PROGRESS(2),
        FAILURE(3);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public enum COMMAND {
        EXEC_MANEUVER(0),
        STOP_MANEUVER(1),
        START_CALIBRATION(2),
        STOP_CALIBRATION(3);

        protected long value;

        public long value() {
            return value;
        }

        COMMAND(long value) {
            this.value = value;
        }
    }

    public VehicleCommand() {
        super(ID_STATIC);
    }

    public VehicleCommand(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VehicleCommand(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VehicleCommand create(Object... values) {
        VehicleCommand m = new VehicleCommand();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VehicleCommand clone(IMCMessage msg) throws Exception {
        VehicleCommand m = new VehicleCommand();
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

    public VehicleCommand setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public VehicleCommand setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public VehicleCommand setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public int getRequestId() {
        return getInteger("request_id");
    }

    public VehicleCommand setRequestId(int request_id) {
        values.put("request_id", request_id);
        return this;
    }

    public COMMAND getCommand() {
        try {
            COMMAND o = COMMAND.valueOf(getMessageType().getFieldPossibleValues("command").get(getLong("command")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getCommandStr() {
        return getString("command");
    }

    public short getCommandVal() {
        return getShort("command");
    }

    public VehicleCommand setCommandStr(String command) {
        setValue("command", command);
        return this;
    }

    public VehicleCommand setCommandVal(COMMAND command) {
        setValue("command", command);
        return this;
    }

    public VehicleCommand setCommand(COMMAND command) {
        values.put("command", command.value());
        return this;
    }

    public Maneuver getManeuver() {
        return getMessageOrNull(Maneuver.class, "Maneuver");
    }

    public <T extends IMCMessage> T getManeuver(Class<T> clazz) throws Exception {
        return getMessage(clazz, "maneuver");
    }

    public VehicleCommand setManeuver(Maneuver maneuver) {
        values.put("maneuver", maneuver);
        return this;
    }

    public int getCalibTime() {
        return getInteger("calib_time");
    }

    public VehicleCommand setCalibTime(int calib_time) {
        values.put("calib_time", calib_time);
        return this;
    }

    public String getInfo() {
        return getString("info");
    }

    public VehicleCommand setInfo(String info) {
        values.put("info", info);
        return this;
    }

}
