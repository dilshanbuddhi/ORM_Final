package org.example.dao.custom.IMPL;

import config.FactoryConfiguration;
import org.example.dao.custom.Course_registrationDao;
import org.hibernate.Session;

import javax.management.Query;
import java.text.DecimalFormat;
import java.util.List;

public class Course_RegistrationDaoImpl implements Course_registrationDao {

    @Override

    public String getNewId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "select max(spid) from Student_programDetail";
        String result = (String) session.createQuery(hql).uniqueResult();
        if (result == null) {
            return "1";
        } else {
            int id = Integer.parseInt(result);
            id = id + 1;
            return String.valueOf(id);
        }
    }
}
