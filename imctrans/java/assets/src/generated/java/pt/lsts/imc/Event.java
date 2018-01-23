package pt.lsts.imc;

public class Event extends IMCMessage {
    public static final int ID_STATIC = 660;


    public Event() {
        super(ID_STATIC);
    }

    public Event(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public Event(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Event create(Object... values) {
        Event m = new Event();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static Event clone(IMCMessage msg) throws Exception {
        Event m = new Event();
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

    public String getTopic() {
        return getString("topic");
    }

    public Event setTopic(String topic) {
        values.put("topic", topic);
        return this;
    }

    public java.util.LinkedHashMap<String, String> getData() {
        return getTupleList("data");
    }

    public Event setData(java.util.LinkedHashMap<String, ?> data) {
        return setData(data);
    }

    public Event setData(String data) {
        values.put("data", data);
        return this;
    }

}
