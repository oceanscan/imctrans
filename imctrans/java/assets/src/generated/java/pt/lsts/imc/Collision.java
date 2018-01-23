package pt.lsts.imc;

public class Collision extends IMCMessage {
    public static final int ID_STATIC = 509;

public static final short CD_X = 0x01;
public static final short CD_Y = 0x02;
public static final short CD_Z = 0x04;
public static final short CD_IMPACT = 0x08;

    public Collision() {
        super(ID_STATIC);
    }

    public Collision(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Collision(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Collision create(Object... values) {
        Collision m = new Collision();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Collision clone(IMCMessage msg) throws Exception {
        Collision m = new Collision();
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

    public double getValue() {
        return getDouble("value");
    }

    public Collision setValue(double value) {
        values.put("value", value);
        return this;
    }

    public short getType() {
        return getShort("type");
    }

    public Collision setType(short type) {
        values.put("type", type);
        return this;
    }

}
