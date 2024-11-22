package com.innovasoft.remodify.platform.information.profiles.domain.model.aggregates;

import com.innovasoft.remodify.platform.iam.domain.model.aggregates.User;
import com.innovasoft.remodify.platform.profiles.domain.model.aggregates.Profile;
import com.innovasoft.remodify.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
public class Remodeler extends AuditableAbstractAggregateRoot<Remodeler> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    private String phone;

    @Getter
    private String description;



    public Remodeler() {
    }

    public Remodeler(String description, String phone, User userId) {
        this.user = userId;
        this.description = description;
        this.phone = phone;
    }


    public long getUserId() {
        return user.getId();
    }


}
