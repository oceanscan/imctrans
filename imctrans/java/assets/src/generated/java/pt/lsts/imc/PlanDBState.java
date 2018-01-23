package pt.lsts.imc;

public class PlanDBState extends IMCMessage {
    public static final int ID_STATIC = 557;


    public PlanDBState() {
        super(ID_STATIC);
    }

    public PlanDBState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanDBState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanDBState create(Object... values) {
        PlanDBState m = new PlanDBState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanDBState clone(IMCMessage msg) throws Exception {
        PlanDBState m = new PlanDBState();
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

    public int getPlanCount() {
        return getInteger("plan_count");
    }

    public PlanDBState setPlanCount(int plan_count) {
        values.put("plan_count", plan_count);
        return this;
    }

    public long getPlanSize() {
        return getLong("plan_size");
    }

    public PlanDBState setPlanSize(long plan_size) {
        values.put("plan_size", plan_size);
        return this;
    }

    public double getChangeTime() {
        return getDouble("change_time");
    }

    public PlanDBState setChangeTime(double change_time) {
        values.put("change_time", change_time);
        return this;
    }

    public int getChangeSid() {
        return getInteger("change_sid");
    }

    public PlanDBState setChangeSid(int change_sid) {
        values.put("change_sid", change_sid);
        return this;
    }

    public String getChangeSname() {
        return getString("change_sname");
    }

    public PlanDBState setChangeSname(String change_sname) {
        values.put("change_sname", change_sname);
        return this;
    }

    public byte[] getMd5() {
        return getRawData("md5");
    }

    public PlanDBState setMd5(byte[] md5) {
        values.put("md5", md5);
        return this;
    }

    public java.util.Vector<PlanDBInformation> getPlansInfo() {
        return getMessageListOrNull("plans_info", PlanDBInformation.class);
    }

    public PlanDBState setPlansInfo(java.util.Collection<PlanDBInformation> plans_info) {
        values.put("plans_info", plans_info);
        return this;
    }

}
