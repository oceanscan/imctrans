package pt.lsts.imc;

public class ReportControl extends IMCMessage {
    public static final int ID_STATIC = 513;

public static final short CI_ACOUSTIC = 0x01;
public static final short CI_SATELLITE = 0x02;
public static final short CI_GSM = 0x04;
public static final short CI_MOBILE = 0x08;

    public enum OP {
        REQUEST_START(0),
        STARTED(1),
        REQUEST_STOP(2),
        STOPPED(3),
        REQUEST_REPORT(4),
        REPORT_SENT(5);

        protected long value;

        public long value() {
            return value;
        }

        OP(long value) {
            this.value = value;
        }
    }

    public ReportControl() {
        super(ID_STATIC);
    }

    public ReportControl(IMCDefinition defs) {
        super(defs, ID_STATIC);
    }

    public ReportControl(IMCMessage msg) {
        super(ID_STATIC);
        try {
            copyFrom(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ReportControl create(Object... values) {
        ReportControl m = new ReportControl();
        for (int i = 0; i < values.length - 1; i += 2)
            m.setValue(values[i].toString(), values[i + 1]);
        return m;
    }

    public static ReportControl clone(IMCMessage msg) throws Exception {
        ReportControl m = new ReportControl();
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

    public ReportControl setOpStr(String op) {
        setValue("op", op);
        return this;
    }

    public ReportControl setOpVal(OP op) {
        setValue("op", op);
        return this;
    }

    public ReportControl setOp(OP op) {
        values.put("op", op.value());
        return this;
    }

    public short getCommInterface() {
        return getShort("comm_interface");
    }

    public ReportControl setCommInterface(short comm_interface) {
        values.put("comm_interface", comm_interface);
        return this;
    }

    public int getPeriod() {
        return getInteger("period");
    }

    public ReportControl setPeriod(int period) {
        values.put("period", period);
        return this;
    }

    public String getSysDst() {
        return getString("sys_dst");
    }

    public ReportControl setSysDst(String sys_dst) {
        values.put("sys_dst", sys_dst);
        return this;
    }

}
