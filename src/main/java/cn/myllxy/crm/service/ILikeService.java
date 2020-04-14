package cn.myllxy.crm.service;

import cn.myllxy.crm.base.service.IBaseService;
import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.vo.req.LikeInfoVo;

import java.util.HashMap;

/**
 * @author myllxy
 * @create 2020-04-13 8:58
 */
public interface ILikeService extends IBaseService<Employee, Long> {

    void likePost(LikeInfoVo likeInfoVo);


    void unLikePost(LikeInfoVo likeInfoVo);

    void saveData2Mysql(HashMap map);
}
