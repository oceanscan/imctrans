package pt.lsts.imc;

public class LeakSimulation extends IMCMessage {
    public static final int ID_STATIC = 51;


    public enum OP {
        OFF(0),
        ON(1);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public LeakSimulation() {
        super(ID_STATIC);
    }

    public LeakSimulation(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public LeakSimulation(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LeakSimulation create(Object... values) {
        LeakSimulation m = new LeakSimulation();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static LeakSimulation clone(IMCMessage msg) throws Exception {
        LeakSimulation m = new LeakSimulation();
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

    public OP getOp() {
        try {
            OP o = OP.valueOf(getMessageType().getFieldPossibleValues("op").get(getLong("op")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getOpStr() {
        return getString("op");
    }

    public short getOpVal() {
        return getShort("op");
    }

    public LeakSimulation setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public LeakSimulation setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public LeakSimulation setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public String getEntities() {
        return getString("entities");
    }

    public LeakSimulation setEntities(String entities) {
        values.put("entities", entities);
        return this;
    }

}
