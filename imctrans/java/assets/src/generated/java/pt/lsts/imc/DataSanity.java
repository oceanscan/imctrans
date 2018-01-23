package pt.lsts.imc;

public class DataSanity extends IMCMessage {
    public static final int ID_STATIC = 284;


    public enum SANE {
        SANE(0),
        NOT_SANE(1);

        protected long value;

        public long value() {
            return value;
        }

        SANE(long value) {
            this.value = value;
        }
    }

    public DataSanity() {
        super(ID_STATIC);
    }

    public DataSanity(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public DataSanity(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSanity create(Object... values) {
        DataSanity m = new DataSanity();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static DataSanity clone(IMCMessage msg) throws Exception {
        DataSanity m = new DataSanity();
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

    public SANE getSane() {
        try {
            SANE o = SANE.valueOf(getMessageType().getFieldPossibleValues("sane").get(getLong("sane")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSaneStr() {
        return getString("sane");
    }

    public short getSaneVal() {
        return getShort("sane");
    }

    public DataSanity setSaneStr(String sane) {
        setValue("sane", sane);
        return this;
    }

    public DataSanity setSaneVal(SANE sane) {
        setValue("sane", sane);
        return this;
    }

    public DataSanity setSane(SANE sane) {
        values.put("sane", sane.value());
        return this;
    }

}
