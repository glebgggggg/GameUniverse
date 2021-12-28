package com.gameUniverse.GameUniverse.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "posts")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    String title;
    String content;
    @ManyToOne
    @JoinColumn(name = "id")
    User user;
    @CreationTimestamp
    private Date createOn;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
