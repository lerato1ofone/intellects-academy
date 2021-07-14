package com.leratoletsepe.intellectsacademyapi.repositories;

import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Note;
import com.leratoletsepe.intellectsacademyapi.models.dto.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.enums.UserType.UserRole;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository implements com.leratoletsepe.intellectsacademyapi.repositories.interfaces.UserRepository {

    private static final String SQL_CREATE = "INSERT INTO IA_USERS(USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE) " +
            "VALUES(NEXTVAL('IA_USERS_SEQ'), ?, ?, ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM IA_USERS WHERE EMAIL = ?";

    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE FROM IA_USERS WHERE USER_ID = ?";

    private static final String SQL_FINDWHOLEUSER_BY_ID = "SELECT USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE, DOB, SEMESTER_MARK, OFFICE_NUMBER, AVATAR, NOTES, COURSES  FROM IA_USERS WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE FROM IA_USERS WHERE EMAIL = ?";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String title, String firstName, String lastName, String email, String password, UserRole role) throws IaBadRequestException {
        try
        {
            // Hashing the user's password before storing on db to encode plain text - using BCrypt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, email);
                ps.setString(5, hashedPassword);
                ps.setObject(6 , role, java.sql.Types.OTHER);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("USER_ID");

        } catch (Exception e){
            throw new IaBadRequestException("Invalid request. Failed to create account.");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws IaBadRequestException {
        try{
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userDtoRowMapper);

            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new IaBadRequestException("Invalid email or password");

            return user;
        }
        catch(EmptyResultDataAccessException ex){
            throw new IaBadRequestException("Invalid email or password.");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) throws IaNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userDtoRowMapper);
        }
        catch(EmptyResultDataAccessException ex){
            throw new IaNotFoundException("User with id " + userId + " not found");
        }
    }

    public com.leratoletsepe.intellectsacademyapi.models.User getUserProfileById(Integer userId) throws IaNotFoundException{
        try{
            return jdbcTemplate.queryForObject(SQL_FINDWHOLEUSER_BY_ID, new Object[]{ userId }, userRowMapper);
        }
        catch(EmptyResultDataAccessException ex){
            throw new IaNotFoundException("User with id " + userId + " not found");
        }
    }

    private RowMapper<User> userDtoRowMapper = ((rs, rowNumber) -> {
        return new User(
                rs.getInt("USER_ID"),
                rs.getString("TITLE"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
               rs.getString("ROLE"));
    });

    private RowMapper<com.leratoletsepe.intellectsacademyapi.models.User> userRowMapper = ((rs, rowNumber) -> {

        var notesResultSet = rs.getString("NOTES");
        var coursesResultSet = rs.getString("COURSES");

        List<Note> notes = notesResultSet == null ? new ArrayList<>() :
                Arrays.asList(new Gson().fromJson(notesResultSet, Note[].class));

        List<Course> courses = coursesResultSet == null ? new ArrayList<>() :
                Arrays.asList(new Gson().fromJson(coursesResultSet, Course[].class));

        return new com.leratoletsepe.intellectsacademyapi.models.User(
                rs.getInt("USER_ID"),
                rs.getString("TITLE"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                UserRole.valueOf(rs.getString("ROLE")),
                rs.getDate("DOB"),
                rs.getDouble("SEMESTER_MARK"),
                rs.getString("OFFICE_NUMBER"),
                rs.getBytes("AVATAR"),
                notes,
                courses
                );
    });
}