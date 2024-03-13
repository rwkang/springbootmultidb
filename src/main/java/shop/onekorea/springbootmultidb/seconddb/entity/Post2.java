package shop.onekorea.springbootmultidb.seconddb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/* @Entity
중요 개념: JPA Dependency 사용하고, application.yml.spring.jpa.hibernate.ddl-auto: create(or create-drop) 일 때,
아래 [@Entity] 어노테이션에 의해, [db.table]이 자동 생성(삭제) 된다.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Post")
public class Post2 {

    /**
     * "UUID" 타입으로 직접 지정하면, 글자가 깨지는 현상이 발생하면서, MySQL DB에서는 제대로 뿌려주질 못하고, null을 뿌린다.
     * 그러므로, 반드시 "String" 타입으로 설정하고, UUID 생성 시에도 반드시 ".toString()"으로 변환하여 저장해야 한다.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Integer id;

    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID postId;
    private String postId;

    //    @Column(length = 255)
    private String title;

    //    @Column(length = 500)
    private String contents;

    //    @Column(nullable = false, length = 100)
    private String author;

    //    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

//    public Post(String postId, String title, String contents, String author, LocalDateTime createdAt) {
//        this.postId = postId;
//        this.title = title;
//        this.contents = contents;
//        this.author = author;
//        this.createdAt = createdAt;
//    }


}

