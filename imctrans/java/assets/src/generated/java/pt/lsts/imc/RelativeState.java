package pt.lsts.imc;

public class RelativeState extends IMCMessage {
    public static final int ID_STATIC = 482;


    public RelativeState() {
        super(ID_STATIC);
    }

    public RelativeState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RelativeState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RelativeState create(Object... values) {
        RelativeState m = new RelativeState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RelativeState clone(IMCMessage msg) throws Exception {
        RelativeState m = new RelativeState();
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

    public String getSId() {
        return getString("s_id");
    }

    public RelativeState setSId(String s_id) {
        values.put("s_id", s_id);
        return this;
    }

    public double getDist() {
        return getDouble("dist");
    }

    public RelativeState setDist(double dist) {
        values.put("dist", dist);
        return this;
    }

    public double getErr() {
        return getDouble("err");
    }

    public RelativeState setErr(double err) {
        values.put("err", err);
        return this;
    }

    public double getCtrlImp() {
        return getDouble("ctrl_imp");
    }

    public RelativeState setCtrlImp(double ctrl_imp) {
        values.put("ctrl_imp", ctrl_imp);
        return this;
    }

    public double getRelDirX() {
        return getDouble("rel_dir_x");
    }

    public RelativeState setRelDirX(double rel_dir_x) {
        values.put("rel_dir_x", rel_dir_x);
        return this;
    }

    public double getRelDirY() {
        return getDouble("rel_dir_y");
    }

    public RelativeState setRelDirY(double rel_dir_y) {
        values.put("rel_dir_y", rel_dir_y);
        return this;
    }

    public double getRelDirZ() {
        return getDouble("rel_dir_z");
    }

    public RelativeState setRelDirZ(double rel_dir_z) {
        values.put("rel_dir_z", rel_dir_z);
        return this;
    }

    public double getErrX() {
        return getDouble("err_x");
    }

    public RelativeState setErrX(double err_x) {
        values.put("err_x", err_x);
        return this;
    }

    public double getErrY() {
        return getDouble("err_y");
    }

    public RelativeState setErrY(double err_y) {
        values.put("err_y", err_y);
        return this;
    }

    public double getErrZ() {
        return getDouble("err_z");
    }

    public RelativeState setErrZ(double err_z) {
        values.put("err_z", err_z);
        return this;
    }

    public double getRfErrX() {
        return getDouble("rf_err_x");
    }

    public RelativeState setRfErrX(double rf_err_x) {
        values.put("rf_err_x", rf_err_x);
        return this;
    }

    public double getRfErrY() {
        return getDouble("rf_err_y");
    }

    public RelativeState setRfErrY(double rf_err_y) {
        values.put("rf_err_y", rf_err_y);
        return this;
    }

    public double getRfErrZ() {
        return getDouble("rf_err_z");
    }

    public RelativeState setRfErrZ(double rf_err_z) {
        values.put("rf_err_z", rf_err_z);
        return this;
    }

    public double getRfErrVx() {
        return getDouble("rf_err_vx");
    }

    public RelativeState setRfErrVx(double rf_err_vx) {
        values.put("rf_err_vx", rf_err_vx);
        return this;
    }

    public double getRfErrVy() {
        return getDouble("rf_err_vy");
    }

    public RelativeState setRfErrVy(double rf_err_vy) {
        values.put("rf_err_vy", rf_err_vy);
        return this;
    }

    public double getRfErrVz() {
        return getDouble("rf_err_vz");
    }

    public RelativeState setRfErrVz(double rf_err_vz) {
        values.put("rf_err_vz", rf_err_vz);
        return this;
    }

    public double getSsX() {
        return getDouble("ss_x");
    }

    public RelativeState setSsX(double ss_x) {
        values.put("ss_x", ss_x);
        return this;
    }

    public double getSsY() {
        return getDouble("ss_y");
    }

    public RelativeState setSsY(double ss_y) {
        values.put("ss_y", ss_y);
        return this;
    }

    public double getSsZ() {
        return getDouble("ss_z");
    }

    public RelativeState setSsZ(double ss_z) {
        values.put("ss_z", ss_z);
        return this;
    }

    public double getVirtErrX() {
        return getDouble("virt_err_x");
    }

    public RelativeState setVirtErrX(double virt_err_x) {
        values.put("virt_err_x", virt_err_x);
        return this;
    }

    public double getVirtErrY() {
        return getDouble("virt_err_y");
    }

    public RelativeState setVirtErrY(double virt_err_y) {
        values.put("virt_err_y", virt_err_y);
        return this;
    }

    public double getVirtErrZ() {
        return getDouble("virt_err_z");
    }

    public RelativeState setVirtErrZ(double virt_err_z) {
        values.put("virt_err_z", virt_err_z);
        return this;
    }

}
