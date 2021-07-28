package com.leratoletsepe.intellectsacademyapi.repositories;

import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Note;
import com.leratoletsepe.intellectsacademyapi.models.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.UserDto;
import com.leratoletsepe.intellectsacademyapi.models.enums.UserType.UserRole;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.IUserRepository;
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
public class UserRepository implements IUserRepository {

    private static final String SQL_CREATE = "INSERT INTO IA_USERS(USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE) " +
            "VALUES(NEXTVAL('IA_USERS_SEQ'), ?, ?, ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM IA_USERS WHERE EMAIL = ?";

    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE FROM IA_USERS WHERE USER_ID = ?";

    private static final String SQL_FINDWHOLEUSER_BY_ID = "SELECT USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE, DOB, SEMESTER_MARK, OFFICE_NUMBER, AVATAR, NOTES, COURSES FROM IA_USERS WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, TITLE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE FROM IA_USERS WHERE EMAIL = ?";

    private static final String SQL_UPDATE = "UPDATE IA_USERS SET TITLE = ?, FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, ROLE = ?, DOB = ?, OFFICE_NUMBER = ?, AVATAR = ?, NOTES = ?, COURSES = ? " +
            "WHERE USER_ID = ?";

    private static final String SQL_DELETE_USER = "DELETE FROM IA_USERS WHERE USER_ID = ?";

    private static final String SQL_DELETE_USER_NOTES = "DELETE FROM IA_NOTES WHERE USER_ID = ?";

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
    public void update(Integer userId, com.leratoletsepe.intellectsacademyapi.models.User user) throws IaBadRequestException {
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
                ps.setString(1, user.getTitle());
                ps.setString(2, user.getFirstName());
                ps.setString(3, user.getLastName());
                ps.setString(4, user.getEmail());
                ps.setObject(5 , user.getUserRole(), java.sql.Types.OTHER);
                ps.setDate(6, user.getDob());
                ps.setString(7, user.getOfficeNumber());
                ps.setBytes(8, user.getAvatar());
                ps.setObject(9, user.getNotes(), java.sql.Types.OTHER);
                ps.setObject(10, user.getCourses(), java.sql.Types.OTHER);
                ps.setInt(11, userId);
                return ps;
            });

        } catch ( Exception e){
            throw new IaBadRequestException("Invalid request details. Failed to update profile");
        }
    }

    @Override
    public void remove(Integer userId) throws IaBadRequestException {
        try {
            jdbcTemplate.query(SQL_DELETE, new Object[]{ userId }, userDtoRowMapper);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new IaNotFoundException("Failed to delete account, try again later");
        }
    }

    @Override
    public UserDto findByEmailAndPassword(String email, String password) throws IaBadRequestException {
        try{
            UserDto user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userDtoRowMapper);

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
    public UserDto findById(Integer userId) throws IaNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{ userId }, userDtoRowMapper);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new IaNotFoundException("User with id " + userId + " not found");
        }
    }

    public User getUserProfileById(Integer userId) throws IaNotFoundException{
        try{
            return jdbcTemplate.queryForObject(SQL_FINDWHOLEUSER_BY_ID, new Object[]{ userId }, userRowMapper);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new IaNotFoundException("User with id " + userId + " not found");
        }
    }

    private RowMapper<UserDto> userDtoRowMapper = ((rs, rowNumber) -> {
        return new UserDto(
                rs.getInt("USER_ID"),
                rs.getString("TITLE"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
               rs.getString("ROLE"));
    });

    private RowMapper<User> userRowMapper = ((rs, rowNumber) -> {

        var notesResultSet = rs.getString("NOTES");
        var coursesResultSet = rs.getString("COURSES");

        List<Note> notes = notesResultSet == null ? new ArrayList<>() :
                Arrays.asList(new Gson().fromJson(notesResultSet, Note[].class));

        List<Course> courses = coursesResultSet == null ? new ArrayList<>() :
                Arrays.asList(new Gson().fromJson(coursesResultSet, Course[].class));

        return new User(
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