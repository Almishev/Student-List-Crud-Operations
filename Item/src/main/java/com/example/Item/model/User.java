package com.example.Item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="u_username")
    private String username;

    @Column(name = "u_userPassword")
    private String password;



}
