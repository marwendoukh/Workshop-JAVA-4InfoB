/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;

/**
 *
 * @author marwen
 */
public interface UserDaoInterface {
    
    List findAllUsers();
    void addUser(User u);
    void updateUser(User u);
    void deleteUser(User u);
    User findUserBy(int username);
     List<User> findUserByName(String name);
     
    
}
