package cn.myllxy.crm.base.domain;

public class BaseDomain {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseDomain() {
    }

    @Override
    public String toString() {
        return "BaseDomain{" +
                "id='" + id + '\'' +
                '}';
    }
}