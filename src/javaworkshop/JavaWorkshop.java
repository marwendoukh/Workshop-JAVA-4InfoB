/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaworkshop;

import dao.UserDao;
import entity.User;

/**
 *
 * @author marwen
 */
public class JavaWorkshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        UserDao userDao= new UserDao();
        userDao.addUser(new User("name", "username"));
        userDao.findUserByName("name");
    }
    
}
