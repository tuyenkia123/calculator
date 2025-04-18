package vn.salary.calculator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.salary.calculator.model.enumeration.Type;

import java.time.LocalDateTime;

@Entity
@Table(name = "`transaction`")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long money;

    private String content;

    @Enumerated(EnumType.STRING)
    private Type type;

    private LocalDateTime createdAt = LocalDateTime.now();
}
