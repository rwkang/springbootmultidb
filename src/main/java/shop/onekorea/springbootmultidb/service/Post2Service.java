package shop.onekorea.springbootmultidb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springbootmultidb.seconddb.entity.Post2;
import shop.onekorea.springbootmultidb.seconddb.repository.Post2Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Post2Service {

    private final Post2Repository post2Repository;

    public List<Post2> getPost2List() {
        List<Post2> post2List = post2Repository.findAll();
        return post2List;
    }
}
