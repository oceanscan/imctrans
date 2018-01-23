package pt.lsts.imc;

public class UASimulation extends IMCMessage {
    public static final int ID_STATIC = 52;


    public enum TYPE {
        DATA(0),
        PING(1),
        PING_REPLY(2);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public UASimulation() {
        super(ID_STATIC);
    }

    public UASimulation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public UASimulation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UASimulation create(Object... values) {
        UASimulation m = new UASimulation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static UASimulation clone(IMCMessage msg) throws Exception {
        UASimulation m = new UASimulation();
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

    public TYPE getType() {
        try {
            TYPE o = TYPE.valueOf(getMessageType().getFieldPossibleValues("type").get(getLong("type")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getTypeStr() {
        return getString("type");
    }

    public short getTypeVal() {
        return getShort("type");
    }

    public UASimulation setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public UASimulation setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public UASimulation setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public int getSpeed() {
        return getInteger("speed");
    }

    public UASimulation setSpeed(int speed) {
        values.put("speed", speed);
        return this;
    }

    public byte[] getData() {
        return getRawData("data");
    }

    public UASimulation setData(byte[] data) {
        values.put("data", data);
        return this;
    }

}
