package pt.lsts.imc;

public class CameraZoom extends IMCMessage {
    public static final int ID_STATIC = 300;


    public enum ACTION {
        ZOOM_RESET(0),
        ZOOM_IN(1),
        ZOOM_OUT(2),
        ZOOM_STOP(3);

        protected long value;

        public long value() {
            return value;
        }

        ACTION(long value) {
            this.value = value;
        }
    }

    public CameraZoom() {
        super(ID_STATIC);
    }

    public CameraZoom(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public CameraZoom(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CameraZoom create(Object... values) {
        CameraZoom m = new CameraZoom();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static CameraZoom clone(IMCMessage msg) throws Exception {
        CameraZoom m = new CameraZoom();
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

    public short getId() {
        return getShort("id");
    }

    public CameraZoom setId(short id) {
        values.put("id", id);
        return this;
    }

    public short getZoom() {
        return getShort("zoom");
    }

    public CameraZoom setZoom(short zoom) {
        values.put("zoom", zoom);
        return this;
    }

    public ACTION getAction() {
        try {
            ACTION o = ACTION.valueOf(getMessageType().getFieldPossibleValues("action").get(getLong("action")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getActionStr() {
        return getString("action");
    }

    public short getActionVal() {
        return getShort("action");
    }

    public CameraZoom setActionStr(String action) {
        setValue("action", action);
        return this;
    }

    public CameraZoom setActionVal(ACTION action) {
        setValue("action", action);
        return this;
    }

    public CameraZoom setAction(ACTION action) {
        values.put("action", action.value());
        return this;
    }

}
