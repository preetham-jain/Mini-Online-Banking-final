package com.example.Mini.Online.Banking.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GenericGenerator(name = "user_id_seq", strategy = "increment")
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.AUTO)

    private int user_id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone_no;
    private String password;
    private String address;
    private String PIN;

    @OneToOne
    @JoinColumn( referencedColumnName = "user_id", name = "id")
    private int id;

}