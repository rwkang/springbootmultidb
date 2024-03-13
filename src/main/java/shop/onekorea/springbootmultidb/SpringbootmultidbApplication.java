/** 2024.02.22 SpringBoot.스프링부트 다중 DB 연결 - 개발자 유미
 * - 하찮은 오후 강의 "Multi DB Connection"과 다른 방법
 * - 강의: https://www.youtube.com/watch?v=kr1qSddBygk
 * - 자료: https://substantial-park-a17.notion.site/5-RDB-bf5aba9e6b644edba1b0f88a3d0894b3?pvs=4
 *
 * -  다중 연결이란?
 * 	- 2개 이상의 데이터베이스를 스프링부트에 연결하는 방법. 스프링부트에서는 하나의 데이터베이스에 대해서만 application.yml 변수 설정을 통해 연결이 가능하기 때문에, 2개 이상부터는 Config 클래스 작성을 통해서만 연결이 가능.
 */

package shop.onekorea.springbootmultidb;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shop.onekorea.springbootmultidb.firstdb.entity.Dept1;
import shop.onekorea.springbootmultidb.firstdb.entity.Post1;
import shop.onekorea.springbootmultidb.firstdb.repository.Dept1Repository;
import shop.onekorea.springbootmultidb.firstdb.repository.Post1Repository;
import shop.onekorea.springbootmultidb.seconddb.entity.Dept2;
import shop.onekorea.springbootmultidb.seconddb.entity.Post2;
import shop.onekorea.springbootmultidb.seconddb.repository.Dept2Repository;
import shop.onekorea.springbootmultidb.seconddb.repository.Post2Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootmultidbApplication implements CommandLineRunner {

    private final Dept1Repository dep1Repository;
    private final Dept2Repository dept2Repository;
    private final Post1Repository post1Repository;
    private final Post2Repository post2Repository;

    /*
    [application.yml] 파일에서 세팅한 값 가져오기 실험...
     */
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String jpaHibernateDdlAuto;
//    public static String jpaHibernateDdlAuto;

//    public static String getApplicationValue() {
//        return jpaHibernateDdlAuto;
//    }

    // Java에서 현재 클래스명, 메소드명, 줄 번호를 가져옵니다.
    public static String getInfo() {

        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        // System.err.println("className: " + className);
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        /**
         * className = "shop.onekorea.springboot.SpringbootApplication"
         * 맨 마지막 클래스 이름만 받기.
         * 이렇게 하면, 아상한 배열이 리턴된다. ∵) 정규식에서 마침표(.)는 임의의 한 문자를 의미 : className.split(".")
         * ∴) 반드시 className.split("\\.") 이렇게 처리해야 한다.
         * @author rwkang
         */

        String lastClassName = className.split("\\.")[className.split("\\.").length - 1];

        String info = lastClassName + "." + lineNumber;

        return info;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmultidbApplication.class, args);

        System.err.println(getInfo());


    }

    @Override
    public void run(String... args) throws Exception {

        System.err.println(getInfo());

        if (jpaHibernateDdlAuto.contains("none")) {
            System.err.println(getInfo() + ", jpaHibernateDdlAuto: " + jpaHibernateDdlAuto);
            return;
        }


//        if (jpaHibernateDdlAuto == "create") {
        if (jpaHibernateDdlAuto.contains("none")) {
            System.err.println(getInfo() + " jpaHibernateDdlAuto: " + jpaHibernateDdlAuto);
            return;
        }

        if (jpaHibernateDdlAuto.contains("create")) {
            Random random = new Random();
            // int max = random.nextInt(10);
            int max = random.nextInt(10 -6) + 6; // from 6 to 10
            System.err.println(getInfo() + " max: " + max);
            if (max < 3) max = 3;

//        List<Post> postList = List.of( // "개발 시에 데이터를 임시로 생성하여 넣어준다.
//                new Post(UUID.randomUUID(), "타이틀 1", "컨텐츠 1", "rwkang", LocalDateTime.now()),
//                new Post(UUID.randomUUID(), "타이틀 2", "컨텐츠 2", "rwkang", LocalDateTime.now()),
//                new Post(UUID.randomUUID(), "타이틀 3", "컨텐츠 3", "rwkang", LocalDateTime.now())
//                );

            List<Dept1> dept1List = new ArrayList<>();
            List<Dept2> dept2List = new ArrayList<>();

            String[] deptNameList = {"관리부", "영업부", "생산부", "자재부", "품질부", "생산 관리부", "기술부", "기획실"};
            String iStr = "";
            for (int i = 1; i < max; i++) {
                iStr = String.format("%04d", i);
                Dept1 dept1 = new Dept1(
                        (long) i,
                        "1001" + iStr,
                        deptNameList[i],
                        LocalDateTime.now(),
                        LocalDateTime.now());
                dept1List.add(dept1);

                // 부서 데이터는 10개만...
                if (i > 9) break;
            }

            for (int i = 1; i < max; i++) {
                iStr = String.format("%04d", i);
                Dept2 dept2 = new Dept2(
                        (long) i,
                        "1001" + iStr,
                        deptNameList[i],
                        LocalDateTime.now(),
                        LocalDateTime.now());
                dept2List.add(dept2);

                // 부서 데이터는 10개만...
                if (i > 9) break;
            }

            System.err.println(getInfo() + ", dept1List: " + dept1List);
            System.err.println(getInfo() + ", dept2List: " + dept2List);
            dep1Repository.saveAll(dept1List);
            dept2Repository.saveAll(dept2List);


            ////////////////////////////////////////////////////////////////////////

            List<Post1> post1List = new ArrayList<>();

            /**
             * UUID V4 : UUID.randomUUID().toString
             * "UUID" 타입으로 직접 지정하면, 글자가 깨지는 현상이 발생하면서, MySQL DB에서는 제대로 뿌려주질 못하고, "null"을 뿌린다.
             * 그러므로, 반드시 "String" 타입으로 설정하고, UUID 생성 시에도 반드시 ".toString()"으로 변환하여 저장해야 한다.
             * 또한,
             * Post.class.createdAt 컬럼 옵션을 "@Builder.Default"로 주고, 값을 LocalDateTime.now()로 주면, 여기서 줄 필요가 없다.
             */

            for (int i = 0; i < max; i++) {
                iStr = String.format("%04d", i);
                Post1 post1 = new Post1(
                        (long) i,
                        UUID.randomUUID().toString(),
                        "타이틀 " + iStr,
                        "컨텐츠 " + iStr,
                        "rwkang321@gmail.com",
                        LocalDateTime.now());
                post1List.add(post1);
            }
            System.err.println(getInfo() + " post1List: " + post1List);
            post1Repository.saveAll(post1List);

            ////////////////////////////////////////////////////////////////////////

            List<Post2> post2List = new ArrayList<>();

            for (int i = 0; i < max; i++) {
                iStr = String.format("%04d", i);
                Post2 post2 = new Post2(
                        (long) i,
                        UUID.randomUUID().toString(),
                        "타이틀 " + iStr,
                        "컨텐츠 " + iStr,
                        "rwkang@naver.com",
                        LocalDateTime.now());
                post2List.add(post2);
            }
            System.err.println(getInfo() + " post2List: " + post2List);
            post2Repository.saveAll(post2List);

            ////////////////////////////////////////////////////////////////////////

        }



    }
}
