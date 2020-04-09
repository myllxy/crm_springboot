package cn.myllxy.crm.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * PageList对象
 * @param <T>
 */
public class PageResult<T> {
    /* 返回总条数 */
    private Long total;
    /* 返回分页数据 */
    private List<T> result = new ArrayList<>();

    public PageResult(){

    }
    public PageResult(Long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", result=" + result +
                '}';
    }
}
