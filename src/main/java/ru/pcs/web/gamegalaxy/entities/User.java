package ru.pcs.web.gamegalaxy.entities;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"firstName", "lastName"})
@ToString(of = {"firstName", "lastName"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    public enum Role {
        ADMIN, USER
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}