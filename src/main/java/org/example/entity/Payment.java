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
    private double remainPayment;

    @OneToOne
    @JoinColumn(name = "sp_id")
    private Student_programDetail studentProgramDetail;

    public Payment(String paymentMethod, String paymentDate, double remainPayment, Student_programDetail studentProgramDetail) {
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.remainPayment = remainPayment;
        this.studentProgramDetail = studentProgramDetail;
    }

    /*  @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;*/

}
