package pt.lsts.imc;

public class AcousticSystems extends IMCMessage {
    public static final int ID_STATIC = 213;


    public AcousticSystems() {
        super(ID_STATIC);
    }

    public AcousticSystems(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public AcousticSystems(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AcousticSystems create(Object... values) {
        AcousticSystems m = new AcousticSystems();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static AcousticSystems clone(IMCMessage msg) throws Exception {
        AcousticSystems m = new AcousticSystems();
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

    public String getList() {
        return getString("list");
    }

    public AcousticSystems setList(String list) {
        values.put("list", list);
        return this;
    }

}
