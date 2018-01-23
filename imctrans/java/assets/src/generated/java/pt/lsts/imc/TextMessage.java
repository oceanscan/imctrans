package pt.lsts.imc;

public class TextMessage extends IMCMessage {
    public static final int ID_STATIC = 160;


    public TextMessage() {
        super(ID_STATIC);
    }

    public TextMessage(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public TextMessage(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TextMessage create(Object... values) {
        TextMessage m = new TextMessage();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static TextMessage clone(IMCMessage msg) throws Exception {
        TextMessage m = new TextMessage();
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

    public String getOrigin() {
        return getString("origin");
    }

    public TextMessage setOrigin(String origin) {
        values.put("origin", origin);
        return this;
    }

    public String getText() {
        return getString("text");
    }

    public TextMessage setText(String text) {
        values.put("text", text);
        return this;
    }

}
