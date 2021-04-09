package com.sanapp.sms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.sanapp.sms.domain.ShopUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        ShopUser user = new ShopUser();
        //user.setId(new Long);
        user.setUserName("ravikumar@gmail.com");
        user.setPassword("ravi2020");
        user.setFirstName("Ravi");
        user.setLastName("Kumar");

        ShopUser savedUser = repo.save(user);

        ShopUser existUser = entityManager.find(ShopUser.class, savedUser.getId());

        assertThat(user.getUserName()).isEqualTo(existUser.getUserName());

    }
}