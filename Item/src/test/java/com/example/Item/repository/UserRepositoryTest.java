package com.example.Item.repository;

import com.example.Item.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

     @Autowired
     private TestEntityManager entityManager;

     @Autowired
     private UserRepository repository;

     @Test
     public void save_StoresRecord_WhenRecordIsValid() {

          User expected = new User();
          expected.setUsername("Anton");
          expected.setUsername("toni86");

          User saved = repository.save(expected);

          User actual = entityManager.find(User.class, saved.getUsername());

          assertThat(actual).isEqualTo(expected);
     }
}