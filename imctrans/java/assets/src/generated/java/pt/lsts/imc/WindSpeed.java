package pt.lsts.imc;

public class WindSpeed extends IMCMessage {
    public static final int ID_STATIC = 271;


    public WindSpeed() {
        super(ID_STATIC);
    }

    public WindSpeed(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public WindSpeed(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WindSpeed create(Object... values) {
        WindSpeed m = new WindSpeed();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static WindSpeed clone(IMCMessage msg) throws Exception {
        WindSpeed m = new WindSpeed();
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

    public double getDirection() {
        return getDouble("direction");
    }

    public WindSpeed setDirection(double direction) {
        values.put("direction", direction);
        return this;
    }

    public double getSpeed() {
        return getDouble("speed");
    }

    public WindSpeed setSpeed(double speed) {
        values.put("speed", speed);
        return this;
    }

    public double getTurbulence() {
        return getDouble("turbulence");
    }

    public WindSpeed setTurbulence(double turbulence) {
        values.put("turbulence", turbulence);
        return this;
    }

}
