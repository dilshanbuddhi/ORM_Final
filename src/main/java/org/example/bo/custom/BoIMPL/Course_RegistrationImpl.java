package org.example.bo.custom.BoIMPL;

import config.FactoryConfiguration;
import org.example.bo.custom.Course_Refistration;
import org.example.dao.DaoFactory;
import org.example.dao.custom.Course_registrationDao;
import org.example.dao.custom.ProgramDao;
import org.example.dao.custom.StudentDao;
import org.example.dto.Student_programDto;
import org.example.entity.Payment;
import org.example.entity.Student;
import org.example.entity.Student_programDetail;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;


public class Course_RegistrationImpl implements Course_Refistration {

   StudentDao studentDao = (StudentDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.STUDENT);
    ProgramDao programDao = (ProgramDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.PROGRAM);
    Course_registrationDao courseRegistrationDao = (Course_registrationDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.COURSE);

    @Override
    public void register(String number, String date, String programId, String stId, String paymentStatus, double amountPaid, double remaining) {

        Student_programDetail studentProgramDetail = new Student_programDetail(number, date,amountPaid, studentDao.search(stId), programDao.search(programId));
        Payment payment = new Payment(paymentStatus, date,remaining,studentProgramDetail);

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

    @Override
    public List<String> getAllProgrambyId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        // Modify the query to use the student ID directly
        String hql = "select program.name from Student_programDetail where student.id = :studentId";
        List<String> programs = session.createQuery(hql, String.class)
                .setParameter("studentId", id)
                .list();
        System.out.println(programs);
        session.getTransaction().commit();
        session.close();

        return programs;
    }

    @Override
    public String getspid(String sid, String pid) {
        return courseRegistrationDao.getspid(sid,pid);
    }




}
