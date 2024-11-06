package org.example.dao;

import org.example.dao.custom.IMPL.ProgramDaoImpl;
import org.example.dao.custom.IMPL.StudetDaoImpl;
import org.example.dao.custom.IMPL.UserDaoImpl;
import org.example.dao.custom.ProgramDao;
import org.example.dao.custom.UserDao;

public class DaoFactory {
    public static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getdaoFactory(){
        return (daoFactory == null) ? daoFactory = new DaoFactory() : daoFactory;
    }



    public enum DaoTypes{
        USER,STUDENT,PROGRAM
    }
    public SuperDao getDao(DaoTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDaoImpl();

            case STUDENT:
                return new StudetDaoImpl();

            case PROGRAM:
                return new ProgramDaoImpl();
        }
        return null;
    }

}
