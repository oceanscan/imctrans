package pt.lsts.imc;

public class ButtonEvent extends IMCMessage {
    public static final int ID_STATIC = 306;


    public ButtonEvent() {
        super(ID_STATIC);
    }

    public ButtonEvent(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ButtonEvent(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ButtonEvent create(Object... values) {
        ButtonEvent m = new ButtonEvent();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ButtonEvent clone(IMCMessage msg) throws Exception {
        ButtonEvent m = new ButtonEvent();
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

    public short getButton() {
        return getShort("button");
    }

    public ButtonEvent setButton(short button) {
        values.put("button", button);
        return this;
    }

    public short getValue() {
        return getShort("value");
    }

    public ButtonEvent setValue(short value) {
        values.put("value", value);
        return this;
    }

}
