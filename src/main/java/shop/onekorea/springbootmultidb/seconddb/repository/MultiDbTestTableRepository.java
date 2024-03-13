package shop.onekorea.springbootmultidb.seconddb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.springbootmultidb.seconddb.entity.MultiDbTestTable;

public interface MultiDbTestTableRepository extends JpaRepository<MultiDbTestTable, Long> {

}
