package cn.myllxy.crm.redis;

import cn.myllxy.crm.service.ILikeService;
import cn.myllxy.crm.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 点赞的定时任务
 */
@Slf4j
public class LikeTask extends QuartzJobBean {
    @Autowired
    ILikeService likeService;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("LikeTask-------- {}", sdf.format(new Date()));
        /* 将Redis里的点赞信息同步到数据库里 */
        HashMap<String, Object> redisLikeData = RedisUtil.getLikeKeys();
        likeService.saveData2Mysql(redisLikeData);
    }
}
