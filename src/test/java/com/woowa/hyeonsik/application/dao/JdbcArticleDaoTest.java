package com.woowa.hyeonsik.application.dao;

import com.woowa.hyeonsik.application.domain.Article;
import com.woowa.hyeonsik.application.domain.User;
import com.woowa.hyeonsik.server.database.DatabaseConnector;
import com.woowa.hyeonsik.server.database.property.MysqlProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

@Disabled
class JdbcArticleDaoTest {
    private JdbcArticleDao articleDao;
    private JdbcUserDao userDao;

    @BeforeEach
    public void setUp() {
        articleDao = new JdbcArticleDao(new DatabaseConnector(new MysqlProperty()));
        userDao = new JdbcUserDao(new DatabaseConnector(new MysqlProperty()));
    }

    @Test
    void write() {
        Article article = new Article(null, "hyeonsik", "title", "cococontents");
        articleDao.save(article);
    }

    @Test
    void read() {
        Optional<Article> byArticleId = articleDao.findByArticleId(1);
        System.out.println(byArticleId.get());
    }

    @Test
    void findAll() {
        List<Article> all = articleDao.findAll();
        System.out.println(all);
    }

    // ------------------------------------------------------------------------------

    @Test
    void userSignup() {
        User user = new User("hello", "passpasword", "hihi", "chgy2131@naver.com");
        userDao.save(user);
    }

    @Test
    void find() {
        User user = new User("abcdef", "passpasword", "hihi", "chgy2131@naver.com");
        userDao.save(user);

        Optional<User> hello = userDao.findByUserId("abcdef");
        System.out.println(hello.get());
    }

    @Test
    void findAllaaaa() {
        System.out.println(userDao.findAll());
    }

    @Test
    void existsUser() {
        System.out.println(userDao.existsByUserId("abcdef"));
        System.out.println(userDao.existsByUserId("sodafhjoiawejfoiawehfioweaj"));
    }
}
