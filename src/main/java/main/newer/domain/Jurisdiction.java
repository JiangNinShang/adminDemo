package main.newer.domain;

public class Jurisdiction {
    private Integer jid;

    private String jname;

    private String jpath;

    private Integer deleted;

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname == null ? null : jname.trim();
    }

    public String getJpath() {
        return jpath;
    }

    public void setJpath(String jpath) {
        this.jpath = jpath == null ? null : jpath.trim();
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}