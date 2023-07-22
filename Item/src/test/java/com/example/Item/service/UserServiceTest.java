package com.example.Item.service;

import com.example.Item.model.User;
import com.example.Item.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {


    @MockBean
    private UserRepository repository;

    private UserService fixture;

    @BeforeEach
    public void setUp() {
        fixture = new UserService(repository);
    }

    @Test
    public void loadUserByUsername_ReturnUser_WhenUserExists() {

          String username = "toni";
          String password = "asroma";

         User user = new User(username, password);

         UserDetails expected = new org.springframework.security.core.userdetails.User(
                username,
                password,
                singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );


        given(repository.findByUsername(username)).willReturn(Optional.of(user));

        final UserDetails actual = fixture.loadUserByUsername(username);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

        then(repository).should().findByUsername(username);
        then(repository).shouldHaveNoMoreInteractions();
    }


    @Test
    public void loadUserByUsername_ReturnUser_WhenUserDoesNotExists() {

        final String username = "toni";

        given(repository.findByUsername(username)).willReturn(Optional.empty());

        assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(() -> fixture.loadUserByUsername(username))
                .withMessageContaining(username);

        then(repository).should().findByUsername(username);
        then(repository).shouldHaveNoMoreInteractions();
    }


    @Test
    public void save_ReturnSaved_WhenUserRecordIsCreated() {

        User expected = new User();

        expected.setUsername("anton");
        expected.setPassword("toni86");

        given(repository.save(expected)).willReturn(expected);

        User actual = fixture.save(expected);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().save(expected);
        then(repository).shouldHaveNoMoreInteractions();
    }


}