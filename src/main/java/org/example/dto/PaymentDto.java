package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDto {
    private String pid;
    private String paymentMethod;
    private String paymentDate;
    private String remainPayment;
    private Student_programDto studentProgramDetail;
}
