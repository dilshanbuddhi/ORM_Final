package org.example.dao.custom.IMPL;

import config.FactoryConfiguration;
import org.example.dao.custom.PaymentDao;
import org.example.entity.Payment;
import org.hibernate.Session;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public Payment getdata(String spid) {
        Session session = FactoryConfiguration.getInstance().getSession();

        String hql = "from Payment where studentProgramDetail.spid = :spid";
        Payment payment = session.createQuery(hql, Payment.class).setParameter("spid", spid).getSingleResult();
        System.out.println(payment.getPaymentMethod() + payment.getPaymentDate() + payment.getRemainPayment());
        return payment;
    }

    @Override
    public boolean refill(String pid, double refillAmount) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        Payment payment = session.get(Payment.class, pid);
        payment.setRemainPayment(payment.getRemainPayment() - refillAmount);
        session.update(payment);
        session.getTransaction().commit();
        return true;
    }
}
