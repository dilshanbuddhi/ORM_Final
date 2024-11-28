package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Student_programDetail;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDto {
    private long pid;
    private String paymentMethod;
    private String paymentDate;
    private double remainPayment;
    private String spid;
}
