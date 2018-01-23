package pt.lsts.imc;

public class SetThrusterActuation extends IMCMessage {
    public static final int ID_STATIC = 301;


    public SetThrusterActuation() {
        super(ID_STATIC);
    }

    public SetThrusterActuation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SetThrusterActuation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SetThrusterActuation create(Object... values) {
        SetThrusterActuation m = new SetThrusterActuation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SetThrusterActuation clone(IMCMessage msg) throws Exception {
        SetThrusterActuation m = new SetThrusterActuation();
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

    public short getId() {
        return getShort("id");
    }

    public SetThrusterActuation setId(short id) {
        values.put("id", id);
        return this;
    }

    public double getValue() {
        return getDouble("value");
    }

    public SetThrusterActuation setValue(double value) {
        values.put("value", value);
        return this;
    }

}
