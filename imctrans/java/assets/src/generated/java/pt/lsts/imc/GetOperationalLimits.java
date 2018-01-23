package pt.lsts.imc;

public class GetOperationalLimits extends IMCMessage {
    public static final int ID_STATIC = 505;


    public GetOperationalLimits() {
        super(ID_STATIC);
    }

    public GetOperationalLimits(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GetOperationalLimits(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GetOperationalLimits create(Object... values) {
        GetOperationalLimits m = new GetOperationalLimits();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GetOperationalLimits clone(IMCMessage msg) throws Exception {
        GetOperationalLimits m = new GetOperationalLimits();
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

}
