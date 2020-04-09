package cn.myllxy.crm.base.service.impl;



import cn.myllxy.crm.base.mapper.BaseMapper;
import cn.myllxy.crm.base.query.BaseQuery;
import cn.myllxy.crm.base.service.IBaseService;
import cn.myllxy.crm.utils.PageResult;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID> {
    /* 调用这个方法的时候发现是个抽象方法,于是去看有没有方法实现了它 */
    public abstract BaseMapper<T, ID> getBaseMapper();

    @Override
    public void save(T t) {
        getBaseMapper().save(t);
    }

    @Override
    public void delete(ID id) {
        getBaseMapper().delete(id);
    }

    @Override
    public void update(T t) {
        getBaseMapper().update(t);
    }

    @Override
    public T selectById(ID id) {
        return getBaseMapper().selectById(id);
    }

    @Override
    public T selectBySn(ID sn) {
        return getBaseMapper().selectBySn(sn);
    }

    @Override
    public List<T> selectAll() {
        return getBaseMapper().selectAll();
    }

    @Override
    public List<T> selectByQuery(BaseQuery baseQuery) {
        return null;
    }

    @Override
    public PageResult<T> selectPageByQuery(BaseQuery baseQuery) {
        Long total = getBaseMapper().getTotalDataCount(baseQuery);
        if (total == 0L) {
            return new PageResult<>();
        }
        List<T> result = getBaseMapper().selectPageByQuery(baseQuery);
        return new PageResult<>(total, result);
    }

    @Override
    public void batchDelete(List<T> list) {
        getBaseMapper().batchDelete(list);
    }

}
