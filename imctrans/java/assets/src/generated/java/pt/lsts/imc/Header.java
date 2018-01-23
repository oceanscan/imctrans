package pt.lsts.imc;

public class Header extends IMCMessage {
    public Header() {
        super(IMCDefinition.getInstance().getHeaderType());
    }

    public Header(IMCDefinition defs) {
        super(defs.getHeaderType());
    }

    public int get_sync() {
        return getInteger("sync");
    }

    public Header set_sync(int sync) {
        values.put("sync", sync);
        return this;
    }

    public int get_mgid() {
        return getInteger("mgid");
    }

    public Header set_mgid(int mgid) {
        values.put("mgid", mgid);
        return this;
    }

    public int get_size() {
        return getInteger("size");
    }

    public Header set_size(int size) {
        values.put("size", size);
        return this;
    }

    public double get_timestamp() {
        return getDouble("timestamp");
    }

    public Header set_timestamp(double timestamp) {
        values.put("timestamp", timestamp);
        return this;
    }

    public int get_src() {
        return getInteger("src");
    }

    public Header set_src(int src) {
        values.put("src", src);
        return this;
    }

    public short get_src_ent() {
        return getShort("src_ent");
    }

    public Header set_src_ent(short src_ent) {
        values.put("src_ent", src_ent);
        return this;
    }

    public int get_dst() {
        return getInteger("dst");
    }

    public Header set_dst(int dst) {
        values.put("dst", dst);
        return this;
    }

    public short get_dst_ent() {
        return getShort("dst_ent");
    }

    public Header set_dst_ent(short dst_ent) {
        values.put("dst_ent", dst_ent);
        return this;
    }

}
