package pt.lsts.imc;

public class NavigationData extends IMCMessage {
    public static final int ID_STATIC = 355;


    public NavigationData() {
        super(ID_STATIC);
    }

    public NavigationData(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public NavigationData(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NavigationData create(Object... values) {
        NavigationData m = new NavigationData();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static NavigationData clone(IMCMessage msg) throws Exception {
        NavigationData m = new NavigationData();
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

    public double getBiasPsi() {
        return getDouble("bias_psi");
    }

    public NavigationData setBiasPsi(double bias_psi) {
        values.put("bias_psi", bias_psi);
        return this;
    }

    public double getBiasR() {
        return getDouble("bias_r");
    }

    public NavigationData setBiasR(double bias_r) {
        values.put("bias_r", bias_r);
        return this;
    }

    public double getCog() {
        return getDouble("cog");
    }

    public NavigationData setCog(double cog) {
        values.put("cog", cog);
        return this;
    }

    public double getCyaw() {
        return getDouble("cyaw");
    }

    public NavigationData setCyaw(double cyaw) {
        values.put("cyaw", cyaw);
        return this;
    }

    public double getLblRejLevel() {
        return getDouble("lbl_rej_level");
    }

    public NavigationData setLblRejLevel(double lbl_rej_level) {
        values.put("lbl_rej_level", lbl_rej_level);
        return this;
    }

    public double getGpsRejLevel() {
        return getDouble("gps_rej_level");
    }

    public NavigationData setGpsRejLevel(double gps_rej_level) {
        values.put("gps_rej_level", gps_rej_level);
        return this;
    }

    public double getCustomX() {
        return getDouble("custom_x");
    }

    public NavigationData setCustomX(double custom_x) {
        values.put("custom_x", custom_x);
        return this;
    }

    public double getCustomY() {
        return getDouble("custom_y");
    }

    public NavigationData setCustomY(double custom_y) {
        values.put("custom_y", custom_y);
        return this;
    }

    public double getCustomZ() {
        return getDouble("custom_z");
    }

    public NavigationData setCustomZ(double custom_z) {
        values.put("custom_z", custom_z);
        return this;
    }

}
