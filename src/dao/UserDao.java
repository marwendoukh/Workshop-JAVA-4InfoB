package dao;

import entity.User;
import java.sql.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnect;

/**
 *
 * @author marwen
 */
public class UserDao implements UserDaoInterface{

    Connection connection;
    /*Advantages of a PreparedStatement:
    --Precompilation and DB-side caching of the SQL statement leads to overall faster execution
    and the ability to reuse the same SQL statement in batches.
    --Automatic prevention of SQL injection attacks by builtin escaping of quotes and other special characters. 
     */
    PreparedStatement pst;
    ResultSet rs;

    public UserDao() {
        connection = DBConnect.getInstanceDB();
    }

    @Override
    public List findAllUsers() {
        String req = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {
                // create new user
                User user = new User();
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                // add to the list
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;

    }

    @Override
    public void addUser(User u) {

        try {

            PreparedStatement ps = connection.prepareStatement("insert into user(`name`, `username`) values(?,?)");
            // use this if you want to include a file
            InputStream is = new FileInputStream(new File("link to file"));

            ps.setString(1, u.getName());
            ps.setString(2, u.getUsername());
            ps.setBlob(3, is);
            // execute query
            ps.executeUpdate();

            System.out.println("added successfully");

        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void updateUser(User u) {

        try {

            PreparedStatement ps = connection.prepareStatement("update user set name=? where username=? ");
            ps.setString(1, u.getName());
            ps.setString(2, u.getUsername());

            ps.executeUpdate();

            System.out.println(" updated successfully");

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteUser(User u) {
        try {

            String req3 = "delete from user where username='" + u.getUsername() + "';";
            pst = connection.prepareStatement(req3);
            pst.executeUpdate(req3);
            System.out.println(" Delete Done!");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User findUserBy(int username) {

        String requete = "select * from user where username=?";

        try {
            User user = new User();
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
            }
            return user;

        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<User> findUserByName(String name) {

        List<User> users = new ArrayList<>();

        try {
            Statement stm = connection.createStatement();
            
            String requete = "select * FROM user WHERE lower(name)like '%" + name + "%' ";
            ResultSet resultat = stm.executeQuery(requete);
            // add all the users to the list
            while (resultat.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                // add to the list
                users.add(user);
            }
            return users;

        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
            return null;
        }

    }

}
