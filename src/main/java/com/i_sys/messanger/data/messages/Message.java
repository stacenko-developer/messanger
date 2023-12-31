package com.i_sys.messanger.data.messages;

import com.i_sys.messanger.data.BaseEntity;
import com.i_sys.messanger.data.users.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Data
@Entity
@Table(name = "message")
@EnableJpaRepositories
public class Message extends BaseEntity {

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    public Message(String text, User sender) {
        this.text = text;
        this.sender = sender;
    }

    public Message() {

    }
}
