package org.example.bo.custom.BoIMPL;

import org.example.bo.custom.PaymentBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.PaymentDao;
import org.example.dto.PaymentDto;
import org.example.entity.Payment;

public class PaymentBoImpl implements PaymentBo {
    PaymentDao paymentDao = (PaymentDao) DaoFactory.daoFactory.getDao(DaoFactory.DaoTypes.PAYMENT);
    @Override
    public PaymentDto getpaymentdetail(String spid) {
        Payment payment = paymentDao.getdata(spid);

        return new PaymentDto(payment.getPid(),payment.getPaymentMethod(),payment.getPaymentDate(),payment.getRemainPayment(),payment.getStudentProgramDetail().getSpid());
    }

    @Override
    public boolean refill(String pid, double refillAmount) {
        return  paymentDao.refill(pid,refillAmount);
    }
}
