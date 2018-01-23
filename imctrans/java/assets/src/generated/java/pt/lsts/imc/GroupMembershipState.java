package pt.lsts.imc;

public class GroupMembershipState extends IMCMessage {
    public static final int ID_STATIC = 180;


    public GroupMembershipState() {
        super(ID_STATIC);
    }

    public GroupMembershipState(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GroupMembershipState(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GroupMembershipState create(Object... values) {
        GroupMembershipState m = new GroupMembershipState();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GroupMembershipState clone(IMCMessage msg) throws Exception {
        GroupMembershipState m = new GroupMembershipState();
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

    public String getGroupName() {
        return getString("group_name");
    }

    public GroupMembershipState setGroupName(String group_name) {
        values.put("group_name", group_name);
        return this;
    }

    public long getLinks() {
        return getLong("links");
    }

    public GroupMembershipState setLinks(long links) {
        values.put("links", links);
        return this;
    }

}
