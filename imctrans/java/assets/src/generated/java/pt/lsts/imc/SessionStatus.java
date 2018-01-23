package pt.lsts.imc;

public class SessionStatus extends IMCMessage {
    public static final int ID_STATIC = 810;


    public enum STATUS {
        ESTABLISHED(1),
        CLOSED(2);

        protected long value;

        public long value() {
            return value;
        }

        STATUS(long value) {
            this.value = value;
        }
    }

    public SessionStatus() {
        super(ID_STATIC);
    }

    public SessionStatus(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public SessionStatus(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionStatus create(Object... values) {
        SessionStatus m = new SessionStatus();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static SessionStatus clone(IMCMessage msg) throws Exception {
        SessionStatus m = new SessionStatus();
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

    public long getSessid() {
        return getLong("sessid");
    }

    public SessionStatus setSessid(long sessid) {
        values.put("sessid", sessid);
        return this;
    }

    public STATUS getStatus() {
        try {
            STATUS o = STATUS.valueOf(getMessageType().getFieldPossibleValues("status").get(getLong("status")));
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    public String getStatusStr() {
        return getString("status");
    }

    public short getStatusVal() {
        return getShort("status");
    }

    public SessionStatus setStatusStr(String status) {
        setValue("status", status);
        return this;
    }

    public SessionStatus setStatusVal(STATUS status) {
        setValue("status", status);
        return this;
    }

    public SessionStatus setStatus(STATUS status) {
        values.put("status", status.value());
        return this;
    }

}
