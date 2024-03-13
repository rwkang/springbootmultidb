package shop.onekorea.springbootmultidb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.onekorea.springbootmultidb.firstdb.entity.Post1;
import shop.onekorea.springbootmultidb.seconddb.entity.Post2;
import shop.onekorea.springbootmultidb.service.Post1Service;
import shop.onekorea.springbootmultidb.service.Post2Service;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostRestController {

    private final Post1Service post1Service;
    private final Post2Service post2Service;

    @GetMapping("/")
    public String getString() {
        return "Post String Test";
    }

    @GetMapping("/list1")
    public List<Post1> getPost1List() {
//        List<Post1> post1List = new ArrayList<>();
        List<Post1> post1List = post1Service.getPost1List();
        return post1List;
    }

    @GetMapping("/list2")
    public List<Post2> post2List() {
//        List<Post2> post2List = new ArrayList<>();
        List<Post2> post2List = post2Service.getPost2List();
        return post2List;
    }

}
