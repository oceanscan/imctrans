package pt.lsts.imc;

public class HistoricTelemetry extends IMCMessage {
    public static final int ID_STATIC = 108;


    public HistoricTelemetry() {
        super(ID_STATIC);
    }

    public HistoricTelemetry(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public HistoricTelemetry(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HistoricTelemetry create(Object... values) {
        HistoricTelemetry m = new HistoricTelemetry();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static HistoricTelemetry clone(IMCMessage msg) throws Exception {
        HistoricTelemetry m = new HistoricTelemetry();
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

    public double getAltitude() {
        return getDouble("altitude");
    }

    public HistoricTelemetry setAltitude(double altitude) {
        values.put("altitude", altitude);
        return this;
    }

    public int getRoll() {
        return getInteger("roll");
    }

    public HistoricTelemetry setRoll(int roll) {
        values.put("roll", roll);
        return this;
    }

    public int getPitch() {
        return getInteger("pitch");
    }

    public HistoricTelemetry setPitch(int pitch) {
        values.put("pitch", pitch);
        return this;
    }

    public int getYaw() {
        return getInteger("yaw");
    }

    public HistoricTelemetry setYaw(int yaw) {
        values.put("yaw", yaw);
        return this;
    }

    public short getSpeed() {
        return getShort("speed");
    }

    public HistoricTelemetry setSpeed(short speed) {
        values.put("speed", speed);
        return this;
    }

}
