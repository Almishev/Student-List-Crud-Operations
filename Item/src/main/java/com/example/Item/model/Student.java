package com.example.Item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "table_students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int id;

    @Column(name = "s_first_name")
    private String firstName;

    @Column(name = "s_last_name")
    private String lastName;
}
