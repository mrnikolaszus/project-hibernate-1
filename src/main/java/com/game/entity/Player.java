package com.game.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(schema = "rpg", name = "player")
@NamedQuery(name = "player_getAllCount", query = "select count (p) from Player p")

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 12, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String title;


    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
     private Race race;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Profession profession;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private Boolean banned;

    @Column(nullable = false)
    private Integer level;

}