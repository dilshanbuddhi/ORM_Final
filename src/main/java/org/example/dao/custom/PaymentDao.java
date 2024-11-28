package org.example.dao.custom;

import org.example.dao.CrudDao;
import org.example.entity.Payment;

public interface PaymentDao extends CrudDao<Payment> {
    Payment getdata(String spid);

    boolean refill(String pid, double refillAmount);
}
