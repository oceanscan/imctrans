package pt.lsts.imc;

public class Sms extends IMCMessage {
    public static final int ID_STATIC = 156;


    public Sms() {
        super(ID_STATIC);
    }

    public Sms(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Sms(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Sms create(Object... values) {
        Sms m = new Sms();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Sms clone(IMCMessage msg) throws Exception {
        Sms m = new Sms();
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

    public String getNumber() {
        return getString("number");
    }

    public Sms setNumber(String number) {
        values.put("number", number);
        return this;
    }

    public int getTimeout() {
        return getInteger("timeout");
    }

    public Sms setTimeout(int timeout) {
        values.put("timeout", timeout);
        return this;
    }

    public String getContents() {
        return getString("contents");
    }

    public Sms setContents(String contents) {
        values.put("contents", contents);
        return this;
    }

}
