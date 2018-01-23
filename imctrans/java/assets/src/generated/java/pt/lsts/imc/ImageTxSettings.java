package pt.lsts.imc;

public class ImageTxSettings extends IMCMessage {
    public static final int ID_STATIC = 703;


    public ImageTxSettings() {
        super(ID_STATIC);
    }

    public ImageTxSettings(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ImageTxSettings(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImageTxSettings create(Object... values) {
        ImageTxSettings m = new ImageTxSettings();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ImageTxSettings clone(IMCMessage msg) throws Exception {
        ImageTxSettings m = new ImageTxSettings();
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

    public short getFps() {
        return getShort("fps");
    }

    public ImageTxSettings setFps(short fps) {
        values.put("fps", fps);
        return this;
    }

    public short getQuality() {
        return getShort("quality");
    }

    public ImageTxSettings setQuality(short quality) {
        values.put("quality", quality);
        return this;
    }

    public short getReps() {
        return getShort("reps");
    }

    public ImageTxSettings setReps(short reps) {
        values.put("reps", reps);
        return this;
    }

    public short getTsize() {
        return getShort("tsize");
    }

    public ImageTxSettings setTsize(short tsize) {
        values.put("tsize", tsize);
        return this;
    }

}
