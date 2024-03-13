package shop.onekorea.springbootmultidb.firstdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Dept")
//@ToString(of = {"id", "deptCode", "deptName", "updatedAt", "createdAt"})
public class Dept1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String deptCode;
    @Column(length = 100)
    private String deptName;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

//    @OneToMany(mappedBy = "dept")
//    private List<User> userList = new ArrayList<>();

//    public Dept(String deptCode, String deptName, LocalDateTime updatedAt, LocalDateTime createdAt) {
//        /**
//         * 여기에 명기를 하지 않은 컬럼은 "SpringbootApplication.class"에서 데이터를 추가할 때, 그 값이 "null"로 들어가네...
//         */
//        this.deptCode = deptCode;
//        this.deptName = deptName;
//        this.updatedAt = updatedAt;
//        this.createdAt = createdAt;
//    }

}