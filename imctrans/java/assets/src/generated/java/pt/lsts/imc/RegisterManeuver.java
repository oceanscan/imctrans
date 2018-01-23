package pt.lsts.imc;

public class RegisterManeuver extends IMCMessage {
    public static final int ID_STATIC = 469;


    public RegisterManeuver() {
        super(ID_STATIC);
    }

    public RegisterManeuver(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RegisterManeuver(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RegisterManeuver create(Object... values) {
        RegisterManeuver m = new RegisterManeuver();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RegisterManeuver clone(IMCMessage msg) throws Exception {
        RegisterManeuver m = new RegisterManeuver();
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

    public int getMid() {
        return getInteger("mid");
    }

    public RegisterManeuver setMid(int mid) {
        values.put("mid", mid);
        return this;
    }

}
