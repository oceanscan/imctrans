package pt.lsts.imc;

public class ControlCommand extends IMCMessage {
    public ControlCommand(int type) {
        super(type);
    }

    public ControlCommand(IMCDefinition defs, int type) {
        super(defs, type);
    }

    public static ControlCommand clone(IMCMessage msg) throws Exception {
        IMCMessage m = IMCDefinition.getInstance().create(msg.getAbbrev());
        if (!Maneuver.class.isAssignableFrom(m.getClass()))
            throw new Exception(m.getClass().getSimpleName() + " is not a subclass of ControlCommand");

        if (msg.definitions != m.definitions) {
            msg = msg.cloneMessage();
            IMCUtil.updateMessage(msg, m.definitions);
        }

        m.getHeader().values.putAll(msg.getHeader().values);
        m.values.putAll(msg.values);

        return (ControlCommand)m;
    }
}
