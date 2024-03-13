package shop.onekorea.springbootmultidb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springbootmultidb.firstdb.entity.Post1;
import shop.onekorea.springbootmultidb.firstdb.repository.Post1Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Post1Service {

    private final Post1Repository post1Repository;

    public List<Post1> getPost1List() {
        List<Post1> post1List = post1Repository.findAll();
        return post1List;
    }
}
