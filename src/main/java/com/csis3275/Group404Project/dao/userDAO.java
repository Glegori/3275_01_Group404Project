package com.csis3275.Group404Project.dao;

import com.csis3275.Group404Project.model.USER_404_PROJECT;
import com.csis3275.Group404Project.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * Executes various SQL queries to be used in the boot controller.
 *
 */
@Component
public class userDAO {

    JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ALL = "select * from USER_404_PROJECT";
    private final String SQL_GET_USER_BY_USERNAME = "select * from USER_404_PROJECT  where USERNAME = ?";
    private final String SQL_GET_USER_BY_ID = "select * from USER_404_PROJECT where ID = ?";

    private final String SQL_UPDATE_USER_BY_USERNAME = "update USER_404_PROJECT set USERNAME = ?, PASSWORD = ?, REPORTSFROM = ?, USERTYPE = ?, TOTAL = ? WHERE USERNAME = ?";
    private final String SQL_UPDATE_PASSWORD_BY_USERNAME = "update USER_404_PROJECT set PASSWORD = ? WHERE USERNAME = ?";
    private final String SQL_UPDATE_USER_TOTAL_BY_USERNAME = "update USER_404_PROJECT set TOTAL = ? WHERE USERNAME = ? ";
    private final String SQL_INSERT_USER = "insert into USER_404_PROJECT(USERNAME, PASSWORD, REPORTSFROM, USERTYPE, TOTAL) values(?,?,?,?,?)";
    private final String SQL_UPDATE_USER_BY_ID = "update USER_404_PROJECT set USERNAME = ?, REPORTSFROM = ?, USERTYPE = ?, TOTAL = ? where ID = ?";
    private final String SQL_DELETE_USER_BY_ID = "delete from USER_404_PROJECT where ID = ?";

    /**
     * Gets the jdbc connection to h2.
     *
     */
    @Autowired
    public userDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Adds a new user to the database.
     * @param user The user class.
     * @return Execution of query to add user.
     */
    public boolean createUser(USER_404_PROJECT user){
        return jdbcTemplate.update(SQL_INSERT_USER, user.getUserName() ,
                user.getPassword(), user.getReportsFrom(),
                user.getUserType(), user.getTotal()) > 0;
    }

    /**
     * Gets the user information based on the username.
     * @param userName The name of the user.
     * @return Query to get a User class.
     */
    public List<USER_404_PROJECT> getUserByUserName(String userName){

        return jdbcTemplate.query(SQL_GET_USER_BY_USERNAME, new Object[] {userName} , new UserMapper());
    }

    /**
     * Updates the user total using to the username, according to the new expense being added.
     * @param userName
     * @param expenseCost
     * @return List of users.
     */
    public boolean updateUserTotal(String userName, Double expenseCost){
        Double currentTotal = getUserByUserName(userName).get(0).getTotal();
        Double newTotal = currentTotal + expenseCost;

        return jdbcTemplate.update(SQL_UPDATE_USER_TOTAL_BY_USERNAME, newTotal, userName) > 0;
    }


    public boolean updateUserByID(USER_404_PROJECT userBeingUpdated) {


        return jdbcTemplate.update(SQL_UPDATE_USER_BY_ID, userBeingUpdated.getUserName(), userBeingUpdated.getReportsFrom(),
                userBeingUpdated.getUserType(), userBeingUpdated.getTotal(), userBeingUpdated.getID()) > 0;
    }



    /**
     * Gets a list of all the users.
     * @return List of users.
     */
    public List<USER_404_PROJECT> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
    }

    /**
     * Grabs the new user password and updates it in the database.
     * @param userName Name of the user.
     * @param newPassword The updated password.
     * @return The execution of the update query to the databse.
     */
    public boolean updatePasswordByUserName(String userName, String newPassword){
        return jdbcTemplate.update(SQL_UPDATE_PASSWORD_BY_USERNAME, newPassword, userName) > 0;
    }


    /**
     * @param id is the id of the user we want to obtain the object for.
     * Gets the user with the matching id from the database.
     * @return USER_404_PROJECT object.
     */
    public USER_404_PROJECT getUserByID(int id) {

        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new Object[] {id} , new UserMapper());
    }


    /**
     * @param id is the id of the user we want to delete from the database.
     * Deletes the user with the matching id as the ID parameter being passed to the method.
     * @return True if the delete was successful. Since the number of rows affected will be 1.
     */
    public boolean deleteUserByID(int id) {

        return jdbcTemplate.update(SQL_DELETE_USER_BY_ID, id) > 0;

    }
}
