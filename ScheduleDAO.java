package com.example.layerd;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class ScheduleDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void createSchedule(String todo, String authorName, String password) {
        String query = "INSERT INTO schedule (todo, author_name, password, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())";
        jdbcTemplate.update(query, todo, authorName, password);
    }


    public List<Schedule> getAllSchedules() {
        String query = "SELECT * FROM schedule ORDER BY updated_at DESC";
        return jdbcTemplate.query(query, (rs, rowNum) -> new Schedule(
                rs.getInt("id"),
                rs.getString("todo"),
                rs.getString("author_name"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        ));
    }


    public Schedule getScheduleById(int id) {
        String query = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> new Schedule(
                rs.getInt("id"),
                rs.getString("todo"),
                rs.getString("author_name"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        ));
    }


    public void updateSchedule(int id, String todo, String authorName, String password) {
        String query = "UPDATE schedule SET todo = ?, author_name = ?, updated_at = NOW() WHERE id = ? AND password = ?";
        int rowsAffected = jdbcTemplate.update(query, todo, authorName, id, password);
        if (rowsAffected == 0) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }


    public void deleteSchedule(int id, String password) {
        String query = "DELETE FROM schedule WHERE id = ? AND password = ?";
        int rowsAffected = jdbcTemplate.update(query, id, password);
        if (rowsAffected == 0) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
}
