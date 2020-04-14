package cn.myllxy.crm.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author myllxy
 * @create 2020-04-13 21:43
 */
@Data
public class LikeInfoVo {
    @NotBlank(message = "当前用户id不能为空")
    private String userId;
    @NotBlank(message = "当前作者id不能为空")
    private String authorId;
    @NotBlank(message = "当前文章id不能为空")
    private String postId;
}
