package org.example.bo;

import org.example.bo.custom.BoIMPL.*;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){
    }
    public static BoFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BoFactory() : boFactory;
    }

    public enum BOTypes{
        USER,STUDENT,PROGRAM,COURSE,PAYMENT
    }


    public SuperBo getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBoImpl();

            case STUDENT:
                return new StudentBoImpl();

            case PROGRAM:
                return new ProgramBoImpl();

            case COURSE:
                return new Course_RegistrationImpl();

            case PAYMENT:
                return new PaymentBoImpl();
        }

        return null;
    }
}
