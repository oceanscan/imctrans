package pt.lsts.imc;

public class FormationMonitor extends IMCMessage {
    public static final int ID_STATIC = 481;


    public FormationMonitor() {
        super(ID_STATIC);
    }

    public FormationMonitor(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public FormationMonitor(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FormationMonitor create(Object... values) {
        FormationMonitor m = new FormationMonitor();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static FormationMonitor clone(IMCMessage msg) throws Exception {
        FormationMonitor m = new FormationMonitor();
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

    public double getAxCmd() {
        return getDouble("ax_cmd");
    }

    public FormationMonitor setAxCmd(double ax_cmd) {
        values.put("ax_cmd", ax_cmd);
        return this;
    }

    public double getAyCmd() {
        return getDouble("ay_cmd");
    }

    public FormationMonitor setAyCmd(double ay_cmd) {
        values.put("ay_cmd", ay_cmd);
        return this;
    }

    public double getAzCmd() {
        return getDouble("az_cmd");
    }

    public FormationMonitor setAzCmd(double az_cmd) {
        values.put("az_cmd", az_cmd);
        return this;
    }

    public double getAxDes() {
        return getDouble("ax_des");
    }

    public FormationMonitor setAxDes(double ax_des) {
        values.put("ax_des", ax_des);
        return this;
    }

    public double getAyDes() {
        return getDouble("ay_des");
    }

    public FormationMonitor setAyDes(double ay_des) {
        values.put("ay_des", ay_des);
        return this;
    }

    public double getAzDes() {
        return getDouble("az_des");
    }

    public FormationMonitor setAzDes(double az_des) {
        values.put("az_des", az_des);
        return this;
    }

    public double getVirtErrX() {
        return getDouble("virt_err_x");
    }

    public FormationMonitor setVirtErrX(double virt_err_x) {
        values.put("virt_err_x", virt_err_x);
        return this;
    }

    public double getVirtErrY() {
        return getDouble("virt_err_y");
    }

    public FormationMonitor setVirtErrY(double virt_err_y) {
        values.put("virt_err_y", virt_err_y);
        return this;
    }

    public double getVirtErrZ() {
        return getDouble("virt_err_z");
    }

    public FormationMonitor setVirtErrZ(double virt_err_z) {
        values.put("virt_err_z", virt_err_z);
        return this;
    }

    public double getSurfFdbkX() {
        return getDouble("surf_fdbk_x");
    }

    public FormationMonitor setSurfFdbkX(double surf_fdbk_x) {
        values.put("surf_fdbk_x", surf_fdbk_x);
        return this;
    }

    public double getSurfFdbkY() {
        return getDouble("surf_fdbk_y");
    }

    public FormationMonitor setSurfFdbkY(double surf_fdbk_y) {
        values.put("surf_fdbk_y", surf_fdbk_y);
        return this;
    }

    public double getSurfFdbkZ() {
        return getDouble("surf_fdbk_z");
    }

    public FormationMonitor setSurfFdbkZ(double surf_fdbk_z) {
        values.put("surf_fdbk_z", surf_fdbk_z);
        return this;
    }

    public double getSurfUnknX() {
        return getDouble("surf_unkn_x");
    }

    public FormationMonitor setSurfUnknX(double surf_unkn_x) {
        values.put("surf_unkn_x", surf_unkn_x);
        return this;
    }

    public double getSurfUnknY() {
        return getDouble("surf_unkn_y");
    }

    public FormationMonitor setSurfUnknY(double surf_unkn_y) {
        values.put("surf_unkn_y", surf_unkn_y);
        return this;
    }

    public double getSurfUnknZ() {
        return getDouble("surf_unkn_z");
    }

    public FormationMonitor setSurfUnknZ(double surf_unkn_z) {
        values.put("surf_unkn_z", surf_unkn_z);
        return this;
    }

    public double getSsX() {
        return getDouble("ss_x");
    }

    public FormationMonitor setSsX(double ss_x) {
        values.put("ss_x", ss_x);
        return this;
    }

    public double getSsY() {
        return getDouble("ss_y");
    }

    public FormationMonitor setSsY(double ss_y) {
        values.put("ss_y", ss_y);
        return this;
    }

    public double getSsZ() {
        return getDouble("ss_z");
    }

    public FormationMonitor setSsZ(double ss_z) {
        values.put("ss_z", ss_z);
        return this;
    }

    public java.util.Vector<RelativeState> getRelState() {
        return getMessageListOrNull("rel_state", RelativeState.class);
    }

    public FormationMonitor setRelState(java.util.Collection<RelativeState> rel_state) {
        values.put("rel_state", rel_state);
        return this;
    }

}
