package com.heeyoung.springboot.web;


import com.heeyoung.springboot.config.auth.LoginUser;
import com.heeyoung.springboot.config.auth.dto.SessionUser;
import com.heeyoung.springboot.service.posts.PostsService;
import com.heeyoung.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        model.addAttribute("len",postsService.findAllDesc().size());

        if(user!=null){
            model.addAttribute("userImage",user.getPicture());
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String postsCheck(@PathVariable Long id,Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        boolean isWriter = user.getEmail().equals(dto.getEmail());
        model.addAttribute("iswriter",isWriter);
        model.addAttribute("post",dto);
        return "posts-check";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user){
        model.addAttribute("userEmail",user.getEmail());
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
