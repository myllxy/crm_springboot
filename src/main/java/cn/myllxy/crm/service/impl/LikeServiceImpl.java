package cn.myllxy.crm.service.impl;

import cn.myllxy.crm.base.mapper.BaseMapper;
import cn.myllxy.crm.base.service.impl.BaseServiceImpl;
import cn.myllxy.crm.constant.RedisConstant;
import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.mapper.EmployeeMapper;
import cn.myllxy.crm.service.ILikeService;
import cn.myllxy.crm.utils.RedisUtil;
import cn.myllxy.crm.vo.req.LikeInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author myllxy
 * @create 2020-04-13 9:07
 */
@Service
@Slf4j
public class LikeServiceImpl extends BaseServiceImpl<Employee, Long> implements ILikeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public BaseMapper<Employee, Long> getBaseMapper() {
        return employeeMapper;
    }

    @Override
    public void likePost(LikeInfoVo likeInfoVo) {
        /* 为指定文章新增指定点赞用户 */
        RedisUtil.sAdd(likeInfoVo.getPostId() + RedisConstant.POINTLIST, likeInfoVo.getUserId());
        String s = RedisUtil.get(likeInfoVo.getPostId() + RedisConstant.POINTCOUNT);
        if (StringUtils.isEmpty(s)) {
            RedisUtil.set(likeInfoVo.getPostId() + RedisConstant.POINTCOUNT, "1");
        } else {
            RedisUtil.set(likeInfoVo.getPostId() + RedisConstant.POINTCOUNT, String.valueOf(Integer.valueOf(s) + 1));
        }
    }

    @Override
    public void unLikePost(LikeInfoVo likeInfoVo) {
        /* 为指定文章移除指定点赞用户 */
        RedisUtil.sRemove(likeInfoVo.getPostId() + RedisConstant.POINTLIST, likeInfoVo.getUserId());
        String s = RedisUtil.get(likeInfoVo.getPostId() + RedisConstant.POINTCOUNT);
        if (!StringUtils.isEmpty(s)) {
            if (s.equals("1")) {
                /* 点赞数删减到0的时候会直接删除这个set */
                RedisUtil.delete(likeInfoVo.getPostId() + RedisConstant.POINTLIST);
            } else {
                /* 点赞数-1 */
                RedisUtil.set(likeInfoVo.getPostId() + RedisConstant.POINTCOUNT, String.valueOf(Integer.valueOf(s) - 1));
            }
        }
    }

    @Override
    public void saveData2Mysql(HashMap map) {

    }


}
