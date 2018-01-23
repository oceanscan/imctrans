package pt.lsts.imc;

public class Maneuver extends IMCMessage {
    public Maneuver(int type) {
        super(type);
    }

    public Maneuver(IMCDefinition defs, int type) {
        super(defs, type);
    }

    public static Maneuver clone(IMCMessage msg) throws Exception {
        IMCMessage m = IMCDefinition.getInstance().create(msg.getAbbrev());
        if (!Maneuver.class.isAssignableFrom(m.getClass()))
            throw new Exception(m.getClass().getSimpleName() + " is not a subclass of Maneuver");

        if (msg.definitions != m.definitions) {
            msg = msg.cloneMessage();
            IMCUtil.updateMessage(msg, m.definitions);
        }

        m.getHeader().values.putAll(msg.getHeader().values);
        m.values.putAll(msg.values);

        return (Maneuver)m;
    }
}
