package org.example.bo.custom.BoIMPL;

import config.FactoryConfiguration;
import org.example.bo.BoFactory;
import org.example.bo.custom.Course_Refistration;
import org.example.bo.custom.ProgramBo;
import org.example.bo.custom.StudentBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.Course_registrationDao;
import org.example.dao.custom.ProgramDao;
import org.example.dao.custom.StudentDao;
import org.example.entity.Payment;
import org.example.entity.Student_programDetail;
import org.hibernate.Session;

import java.io.Serializable;


public class Course_RegistrationImpl implements Course_Refistration {

   StudentDao studentDao = (StudentDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.STUDENT);
    ProgramDao programDao = (ProgramDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.PROGRAM);
    Course_registrationDao courseRegistrationDao = (Course_registrationDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.COURSE);

    @Override
    public void register(String number, String date, String programId, String stId, String paymentStatus, String amountPaid) {

        Student_programDetail studentProgramDetail = new Student_programDetail(number, date, studentDao.search(stId), programDao.search(programId));
        Payment payment = new Payment(paymentStatus, date,amountPaid ,studentProgramDetail);

        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        try {
            boolean issaved = courseRegistrationDao.save(studentProgramDetail,session);
            if (issaved){
                   Serializable isSaved = (Serializable) session.save(payment);
                   if (isSaved!= null){
                       session.getTransaction().commit();
                   }    else {
                       session.getTransaction().rollback();
                   }
            }else {
                session.getTransaction().rollback();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

      /*  Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.save(studentProgramDetail);
        session.save(payment);
        session.getTransaction().commit();*/


    }
}