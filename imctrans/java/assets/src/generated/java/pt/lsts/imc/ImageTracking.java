package pt.lsts.imc;

public class ImageTracking extends Maneuver {
    public static final int ID_STATIC = 490;


    public ImageTracking() {
        super(ID_STATIC);
    }

    public ImageTracking(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ImageTracking(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImageTracking create(Object... values) {
        ImageTracking m = new ImageTracking();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ImageTracking clone(IMCMessage msg) throws Exception {
        ImageTracking m = new ImageTracking();
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

}
