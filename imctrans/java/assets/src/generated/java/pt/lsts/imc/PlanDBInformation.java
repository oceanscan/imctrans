package pt.lsts.imc;

public class PlanDBInformation extends IMCMessage {
    public static final int ID_STATIC = 558;


    public PlanDBInformation() {
        super(ID_STATIC);
    }

    public PlanDBInformation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public PlanDBInformation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PlanDBInformation create(Object... values) {
        PlanDBInformation m = new PlanDBInformation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static PlanDBInformation clone(IMCMessage msg) throws Exception {
        PlanDBInformation m = new PlanDBInformation();
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

    public PlanDBInformation setPlanId(String plan_id) {
        values.put("plan_id", plan_id);
        return this;
    }

    public int getPlanSize() {
        return getInteger("plan_size");
    }

    public PlanDBInformation setPlanSize(int plan_size) {
        values.put("plan_size", plan_size);
        return this;
    }

    public double getChangeTime() {
        return getDouble("change_time");
    }

    public PlanDBInformation setChangeTime(double change_time) {
        values.put("change_time", change_time);
        return this;
    }

    public int getChangeSid() {
        return getInteger("change_sid");
    }

    public PlanDBInformation setChangeSid(int change_sid) {
        values.put("change_sid", change_sid);
        return this;
    }

    public String getChangeSname() {
        return getString("change_sname");
    }

    public PlanDBInformation setChangeSname(String change_sname) {
        values.put("change_sname", change_sname);
        return this;
    }

    public byte[] getMd5() {
        return getRawData("md5");
    }

    public PlanDBInformation setMd5(byte[] md5) {
        values.put("md5", md5);
        return this;
    }

}
