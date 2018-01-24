package pt.lsts.imc;

public class Dislodge extends Maneuver {
    public static final int ID_STATIC = 483;


    public enum DIRECTION {
        AUTO(0),
        FORWARD(1),
        BACKWARD(2);

        protected long value;

        public long value() {
            return value;
        }

        DIRECTION(long value) {
            this.value = value;
        }
    }

    public Dislodge() {
        super(ID_STATIC);
    }

    public Dislodge(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Dislodge(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Dislodge create(Object... values) {
        Dislodge m = new Dislodge();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Dislodge clone(IMCMessage msg) throws Exception {
        Dislodge m = new Dislodge();
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

    public int getTimeout() {
        return getInteger("timeout");
    }

    public Dislodge setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public double getRpm() {
        return getDouble("rpm");
    }

    public Dislodge setRpm(double rpm) {
        values.put("rpm", rpm);
        return this;
    }

    public DIRECTION getDirection() {
        try {
            DIRECTION o = DIRECTION.valueOf(getMessageType().getFieldPossibleValues("direction").get(getLong("direction")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getDirectionStr() {
        return getString("direction");
    }

    public short getDirectionVal() {
        return getShort("direction");
    }

    public Dislodge setDirectionStr(String direction) {
        setValue("direction", direction);
        return this;
    }

    public Dislodge setDirectionVal(DIRECTION direction) {
        setValue("direction", direction);
        return this;
    }

    public Dislodge setDirection(DIRECTION direction) {
        values.put("direction", direction.value());
        return this;
    }

    public java.util.LinkedHashMap<String, String> getCustom() {
        return getTupleList("custom");
    }

    public Dislodge setCustom(java.util.LinkedHashMap<String, ?> custom) {
        values.put("custom", encodeTupleList(custom));
        return this;
    }

    public Dislodge setCustom(String custom) {
        values.put("custom", custom);
        return this;
    }

}
