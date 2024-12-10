package com.example.layerd;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleDAO scheduleDAO;

    public ScheduleService(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public void createSchedule(String todo, String authorName, String password) {
        scheduleDAO.createSchedule(todo, authorName, password);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleDAO.getAllSchedules();
    }

    public Schedule getScheduleById(int id) {
        return scheduleDAO.getScheduleById(id);
    }

    public void updateSchedule(int id, String todo, String authorName, String password) {
        scheduleDAO.updateSchedule(id, todo, authorName, password);
    }

    public void deleteSchedule(int id, String password) {
        scheduleDAO.deleteSchedule(id, password);
    }
}
