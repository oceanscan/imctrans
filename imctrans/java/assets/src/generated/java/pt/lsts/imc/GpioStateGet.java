package pt.lsts.imc;

public class GpioStateGet extends IMCMessage {
    public static final int ID_STATIC = 2001;


    public GpioStateGet() {
        super(ID_STATIC);
    }

    public GpioStateGet(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public GpioStateGet(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GpioStateGet create(Object... values) {
        GpioStateGet m = new GpioStateGet();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static GpioStateGet clone(IMCMessage msg) throws Exception {
        GpioStateGet m = new GpioStateGet();
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

    public String getName() {
        return getString("name");
    }

    public GpioStateGet setName(String name) {
        values.put("name", name);
        return this;
    }

}
