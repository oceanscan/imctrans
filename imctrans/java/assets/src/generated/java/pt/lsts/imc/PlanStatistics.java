package pt.lsts.imc;

public class PlanStatistics extends IMCMessage {
    public static final int ID_STATIC = 564;

public static final short PRP_BASIC = 0x00;
public static final short PRP_NONLINEAR = 0x01;
public static final short PRP_INFINITE = 0x02;
public static final short PRP_CYCLICAL = 0x04;
public static final short PRP_ALL = 0x07;

    public enum TYPE {
        PREPLAN(0),
        INPLAN(1),
        POSTPLAN(2);

        protected long value;

        public long value() {
            return value;
        }

        TYPE(long value) {
            this.value = value;
        }
    }

    public PlanStatistics() {
        super(ID_STATIC);
    }

    public PlanStatistics(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanStatistics(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanStatistics create(Object... values) {
        PlanStatistics m = new PlanStatistics();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanStatistics clone(IMCMessage msg) throws Exception {
        PlanStatistics m = new PlanStatistics();
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

    public String getPlanId() {
        return getString("plan_id");
    }

    public PlanStatistics setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
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

    public PlanStatistics setTypeStr(String type) {
        setValue("type", type);
        return this;
    }

    public PlanStatistics setTypeVal(TYPE type) {
        setValue("type", type);
        return this;
    }

    public PlanStatistics setType(TYPE type) {
        values.put("type", type.value());
        return this;
    }

    public short getProperties() {
        return getShort("properties");
    }

    public PlanStatistics setProperties(short properties) {
        values.put("properties", properties);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getDurations() {
        return getTupleList("durations");
    }

    public PlanStatistics setDurations(java.util.LinkedHashMap<String, ?> durations) {
        values.put("durations", encodeTupleList(durations));
        return this;
    }

    public PlanStatistics setDurations(String durations) {
        values.put("durations", durations);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getDistances() {
        return getTupleList("distances");
    }

    public PlanStatistics setDistances(java.util.LinkedHashMap<String, ?> distances) {
        values.put("distances", encodeTupleList(distances));
        return this;
    }

    public PlanStatistics setDistances(String distances) {
        values.put("distances", distances);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getActions() {
        return getTupleList("actions");
    }

    public PlanStatistics setActions(java.util.LinkedHashMap<String, ?> actions) {
        values.put("actions", encodeTupleList(actions));
        return this;
    }

    public PlanStatistics setActions(String actions) {
        values.put("actions", actions);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getFuel() {
        return getTupleList("fuel");
    }

    public PlanStatistics setFuel(java.util.LinkedHashMap<String, ?> fuel) {
        values.put("fuel", encodeTupleList(fuel));
        return this;
    }

    public PlanStatistics setFuel(String fuel) {
        values.put("fuel", fuel);
        return this;
    }

}
