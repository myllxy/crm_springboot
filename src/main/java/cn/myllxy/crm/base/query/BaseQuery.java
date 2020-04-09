package cn.myllxy.crm.base.query;

public class BaseQuery {
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    //开始位置
    public Integer getBegin(){
        return (currentPage-1)*this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}