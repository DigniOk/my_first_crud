package ru.gb.my_first_crud.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.my_first_crud.Model.User;

import java.util.List;

@Repository
public class UserRepository {


    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbc.query(sql, userRowMapper);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id =" + id;
        RowMapper<User> userRowMapper = (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            return user;
        };
        return jdbc.queryForObject(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName,lastName) VALUES ( ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id =" + id;
        jdbc.update(sql);
    }

    public void updateById(int id, User newUser) {
        User user = findById(id);
        if (user != null) {
            String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
            jdbc.update(sql, newUser.getFirstName(), newUser.getLastName(), newUser.getId());
        }
    }
}