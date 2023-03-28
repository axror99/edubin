package com.example.edubin.data;

import com.example.edubin.controller.AuthenticationController;
import com.example.edubin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class CommonIntegrationTest {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected CommentRepository commentRepository;
    @Autowired
    protected BlogRepository blogRepository;
    @Autowired
    protected ContentRepository contentRepository;

    @Autowired
    protected AuthenticationController authenticationController;


    @Autowired
    protected TestDataHelperUser testDataHelperUser;
    @Autowired
    protected TestDataHelperEmployee testDataHelperEmployee;
    @Autowired
    protected TestDataHelperBlog testDataHelperBlog;
    @Autowired
    protected TestDataHelperCategory testDataHelperCategory;
    @Autowired
    protected TestDataHelperComment testDataHelperComment;
    @Autowired
    protected TestDataHelperContent testDataHelperContent;
//    @AfterEach
//    void testDataCleanUp() {
//        userRepository.deleteAll();
//    }

    private static final String IMAGE_NAME = "postgres:latest";

    protected static final PostgreSQLContainer<?> postgres;



    static {
        postgres = (PostgreSQLContainer) new PostgreSQLContainer(DockerImageName.parse(IMAGE_NAME))
               // .withInitScript("sql/table-init.sql")
                .withExposedPorts(5432);
        postgres.withReuse(true);
//        Startables.deepStart(postgres).join();
    }

    @DynamicPropertySource
    static void setUpPostgresConnectionProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.database", postgres::getDatabaseName);
    }
}
