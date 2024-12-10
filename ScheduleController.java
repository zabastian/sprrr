package com.example.layerd;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //restcontroller 사용후 requestmapping으로 데이터 가져옴
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping //일정 생성을 하기 위해 데이터를 요청해야한다.
    public void createSchedule(@RequestBody ScheduleRequest request) { //requestbody로 요청본문에 사용한 데이터 불러온다
        scheduleService.createSchedule(request.getTodo(), request.getAuthorName(), request.getPassword());
    }

    @GetMapping //getmapping으로 일단 전체 일정을 조회함
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{id}") //pathvaroable을 통해 매개변수 바인딩 후 id값을 주어 특정한 일정을 조회한다.
    public Schedule getScheduleById(@PathVariable int id) {
        return scheduleService.getScheduleById(id);
    }


    @PutMapping("/{id}") // 일정을 수정해야 하기때문에 다시 requestbody 사용
    public void updateSchedule(@PathVariable int id, @RequestBody ScheduleRequest request) {
        scheduleService.updateSchedule(id, request.getTodo(), request.getAuthorName(), request.getPassword());
    }


    @DeleteMapping("/{id}") // 처음에 requestbody를 이용하다 나중에 본문에서 데이터를 받아오는 클래스 만들었으나 오류가 너무많이 생겨
                              // 결국에 requestparam으로 chatgpt 도움을 받았다.(이부분 사용)
    public void deleteSchedule(@PathVariable int id, @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
    }
}
