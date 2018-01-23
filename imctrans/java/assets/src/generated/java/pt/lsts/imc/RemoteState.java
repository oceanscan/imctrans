package pt.lsts.imc;

public class RemoteState extends IMCMessage {
    public static final int ID_STATIC = 750;


    public RemoteState() {
        super(ID_STATIC);
    }

    public RemoteState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RemoteState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RemoteState create(Object... values) {
        RemoteState m = new RemoteState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RemoteState clone(IMCMessage msg) throws Exception {
        RemoteState m = new RemoteState();
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

    public double getLat() {
        return getDouble("lat");
    }

    public RemoteState setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public RemoteState setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public short getDepth() {
        return getShort("depth");
    }

    public RemoteState setDepth(short depth) {
        values.put("depth", depth);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public RemoteState setSpeed(double speed) {
        values.put("speed", speed);
        return this;
    }

    public double getPsi() {
        return getDouble("psi");
    }

    public RemoteState setPsi(double psi) {
        values.put("psi", psi);
        return this;
    }

}
