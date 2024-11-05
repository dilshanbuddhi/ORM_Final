package org.example.bo;

import org.example.bo.custom.BoIMPL.UserBoImpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){
    }
    public static BoFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BoFactory() : boFactory;
    }

    public enum BOTypes{
        USER,
    }


    public SuperBo getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBoImpl();
        }
        return null;
    }
}
