package org.dsi.com.questionService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long Id;
    @Column(unique = true)
    private String Name;
    private Boolean isDeleted;
}
