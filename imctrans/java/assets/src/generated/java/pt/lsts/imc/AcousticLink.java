package pt.lsts.imc;

public class AcousticLink extends IMCMessage {
    public static final int ID_STATIC = 214;


    public AcousticLink() {
        super(ID_STATIC);
    }

    public AcousticLink(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AcousticLink(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AcousticLink create(Object... values) {
        AcousticLink m = new AcousticLink();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AcousticLink clone(IMCMessage msg) throws Exception {
        AcousticLink m = new AcousticLink();
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

    public String getPeer() {
        return getString("peer");
    }

    public AcousticLink setPeer(String peer) {
        values.put("peer", peer);
        return this;
    }

    public double getRssi() {
        return getDouble("rssi");
    }

    public AcousticLink setRssi(double rssi) {
        values.put("rssi", rssi);
        return this;
    }

    public int getIntegrity() {
        return getInteger("integrity");
    }

    public AcousticLink setIntegrity(int integrity) {
        values.put("integrity", integrity);
        return this;
    }

}
