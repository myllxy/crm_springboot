package cn.myllxy.crm.controller;

import cn.myllxy.crm.service.ILikeService;
import cn.myllxy.crm.vo.req.LikeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author myllxy
 * @create 2020-04-13 8:57
 */
@Controller
@CrossOrigin
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private ILikeService likeService;

    @PostMapping("/likepost")
    @ResponseBody
    public void likePost(@RequestBody @Valid LikeInfoVo likeInfoVo) {
        likeService.likePost(likeInfoVo);
    }

    @PostMapping("/unlikepost")
    @ResponseBody
    public void unLikePost(@RequestBody @Valid LikeInfoVo likeInfoVo) {
        likeService.unLikePost(likeInfoVo);
    }
}
