package org.example.bo.custom;

import org.example.bo.SuperBo;
import org.example.dto.PaymentDto;

public interface PaymentBo extends SuperBo {
    PaymentDto getpaymentdetail(String spid);

    boolean refill(String pid, double refillAmount);
}
