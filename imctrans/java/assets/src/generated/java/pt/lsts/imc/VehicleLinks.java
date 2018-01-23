package pt.lsts.imc;

public class VehicleLinks extends IMCMessage {
    public static final int ID_STATIC = 650;


    public VehicleLinks() {
        super(ID_STATIC);
    }

    public VehicleLinks(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public VehicleLinks(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VehicleLinks create(Object... values) {
        VehicleLinks m = new VehicleLinks();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static VehicleLinks clone(IMCMessage msg) throws Exception {
        VehicleLinks m = new VehicleLinks();
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

    public String getLocalname() {
        return getString("localname");
    }

    public VehicleLinks setLocalname(String localname) {
        values.put("localname", localname);
        return this;
    }

    public java.util.Vector<Announce> getLinks() {
        return getMessageListOrNull("links", Announce.class);
    }

    public VehicleLinks setLinks(java.util.Collection<Announce> links) {
        values.put("links", links);
        return this;
    }

}
