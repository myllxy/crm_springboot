package cn.myllxy.crm.base.mapper;

import cn.myllxy.crm.base.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T, ID extends Serializable> {
    void save(T t);

    void delete(ID id);

    void update(T t);

    T selectById(ID id);

    T selectBySn(ID sn);

    List<T> selectAll();

    List<T> selectPageByQuery(BaseQuery baseQuery);

    Long getTotalDataCount(BaseQuery baseQuery);

    void batchDelete(List<T> list);

    List<String> selectName();
}