package pt.lsts.imc;

public class SetImageCoords extends IMCMessage {
    public static final int ID_STATIC = 895;


    public SetImageCoords() {
        super(ID_STATIC);
    }

    public SetImageCoords(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SetImageCoords(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SetImageCoords create(Object... values) {
        SetImageCoords m = new SetImageCoords();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SetImageCoords clone(IMCMessage msg) throws Exception {
        SetImageCoords m = new SetImageCoords();
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

    public short getCamid() {
        return getShort("camId");
    }

    public SetImageCoords setCamid(short camId) {
        values.put("camId", camId);
        return this;
    }

    public int getX() {
        return getInteger("x");
    }

    public SetImageCoords setX(int x) {
        values.put("x", x);
        return this;
    }

    public int getY() {
        return getInteger("y");
    }

    public SetImageCoords setY(int y) {
        values.put("y", y);
        return this;
    }

}
