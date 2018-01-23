package pt.lsts.imc;

public class Reference extends IMCMessage {
    public static final int ID_STATIC = 479;

public static final short FLAG_LOCATION = 0x01;
public static final short FLAG_SPEED = 0x02;
public static final short FLAG_Z = 0x04;
public static final short FLAG_RADIUS = 0x08;
public static final short FLAG_START_POINT = 0x10;
public static final short FLAG_DIRECT = 0x20;
public static final short FLAG_MANDONE = 0x80;

    public Reference() {
        super(ID_STATIC);
    }

    public Reference(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Reference(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Reference create(Object... values) {
        Reference m = new Reference();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Reference clone(IMCMessage msg) throws Exception {
        Reference m = new Reference();
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

    public short getFlags() {
        return getShort("flags");
    }

    public Reference setFlags(short flags) {
        values.put("flags", flags);
        return this;
    }

    public DesiredSpeed getSpeed() {
        return getMessageOrNull(DesiredSpeed.class, "DesiredSpeed");
    }

    public <T extends IMCMessage> T getSpeed(Class<T> clazz) throws Exception {
        return getMessage(clazz, "speed");
    }

    public Reference setSpeed(DesiredSpeed speed) {
        values.put("speed", speed);
        return this;
    }

    public DesiredZ getZ() {
        return getMessageOrNull(DesiredZ.class, "DesiredZ");
    }

    public <T extends IMCMessage> T getZ(Class<T> clazz) throws Exception {
        return getMessage(clazz, "z");
    }

    public Reference setZ(DesiredZ z) {
        values.put("z", z);
        return this;
    }

    public double getLat() {
        return getDouble("lat");
    }

    public Reference setLat(double lat) {
        values.put("lat", lat);
        return this;
    }

    public double getLon() {
        return getDouble("lon");
    }

    public Reference setLon(double lon) {
        values.put("lon", lon);
        return this;
    }

    public double getRadius() {
        return getDouble("radius");
    }

    public Reference setRadius(double radius) {
        values.put("radius", radius);
        return this;
    }

}
