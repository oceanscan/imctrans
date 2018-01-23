package pt.lsts.imc;

public class RemoteCommand extends RemoteData {
    public static final int ID_STATIC = 188;


    public RemoteCommand() {
        super(ID_STATIC);
    }

    public RemoteCommand(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public RemoteCommand(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RemoteCommand create(Object... values) {
        RemoteCommand m = new RemoteCommand();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static RemoteCommand clone(IMCMessage msg) throws Exception {
        RemoteCommand m = new RemoteCommand();
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

    public int getOriginalSource() {
        return getInteger("original_source");
    }

    public RemoteCommand setOriginalSource(int original_source) {
        values.put("original_source", original_source);
        return this;
    }

    public int getDestination() {
        return getInteger("destination");
    }

    public RemoteCommand setDestination(int destination) {
        values.put("destination", destination);
        return this;
    }

    public double getTimeout() {
        return getDouble("timeout");
    }

    public RemoteCommand setTimeout(double timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public IMCMessage getCmd() {
        return getMessageOrNull(IMCMessage.class, "IMCMessage");
    }

    public <T extends IMCMessage> T getCmd(Class<T> clazz) throws Exception {
        return getMessage(clazz, "cmd");
    }

    public RemoteCommand setCmd(IMCMessage cmd) {
        values.put("cmd", cmd);
        return this;
    }

}
