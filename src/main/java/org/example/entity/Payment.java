package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;
    private String paymentMethod;
    private String paymentDate;
    private String remainPayment;

    @OneToOne
    @JoinColumn(name = "sp_id")
    private Student_programDetail studentProgramDetail;

  /*  @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;*/

}