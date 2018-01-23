package pt.lsts.imc;

public class GetImageCoords extends IMCMessage {
    public static final int ID_STATIC = 896;


    public GetImageCoords() {
        super(ID_STATIC);
    }

    public GetImageCoords(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GetImageCoords(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GetImageCoords create(Object... values) {
        GetImageCoords m = new GetImageCoords();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GetImageCoords clone(IMCMessage msg) throws Exception {
        GetImageCoords m = new GetImageCoords();
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

    public GetImageCoords setCamid(short camId) {
        values.put("camId", camId);
        return this;
    }

    public int getX() {
        return getInteger("x");
    }

    public GetImageCoords setX(int x) {
        values.put("x", x);
        return this;
    }

    public int getY() {
        return getInteger("y");
    }

    public GetImageCoords setY(int y) {
        values.put("y", y);
        return this;
    }

}
