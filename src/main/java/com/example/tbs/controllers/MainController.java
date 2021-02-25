package com.example.tbs.controllers;

import com.example.tbs.comparators.NewnessComparator;
import com.example.tbs.comparators.StatusComparator;
import com.example.tbs.comparators.TimeComparator;
import com.example.tbs.dataMapper.*;
import com.example.tbs.dataMapper.oracleMappers.*;
import com.example.tbs.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@SessionAttributes(value = "user")
public class MainController {

    private User user;
    private static List<Event> list;
    static Comparator<Event> comparator = new StatusComparator();
    private IEventMapper eventMapper = new EventMapper();
    private IStatusMapper statusMapper = new StatusMapper();
    private IAreaMapper areaMapper = new AreaMapper();
    private IOrderMapper orderMapper = new OrderMapper();
    private IUserMapper userMapper = new UserMapper();
    private IStatisticMapper statisticMapper = new StatisticMapper();


    @ModelAttribute("user")
    public User getUser() {
        return user;
    }

    private void reset(){
        switch (user.getRole()){
            case "admin":
                list = eventMapper.getAll();
                break;
            case "customer":
                list = orderMapper.getMyOrders(user.getId());
                break;
        }
    }

    @PostMapping("/getUser")
    String enter(@RequestParam String login, @RequestParam String password){
        this.user = userMapper.getEntity(login,password);
        if(user!=null){
            reset();
            return "redirect:"+user.getRole();
        }
        else return "authorization";
    }

    @GetMapping("/admin")
    String adminMain(Model model) {
        model.addAttribute("user",user);
        Collections.sort(list, comparator);
        model.addAttribute("events", list);
        return "admin";
    }

    @GetMapping("/byStatusDown")
    String filterStatusDown(){
        MainController.comparator = new StatusComparator();
        return "redirect:/"+user.getRole();
    }

    @GetMapping("/byStatusUp")
    String filterStatusUp(){
        MainController.comparator = new StatusComparator().reversed();
        return "redirect:/"+user.getRole();
    }

    @GetMapping("/byTimeDown")
    String filterTimeDown(){
        MainController.comparator = new TimeComparator();
        return "redirect:/"+user.getRole();
    }

    @GetMapping("/byTimeUp")
    String filterTimeUp(){
        MainController.comparator = new TimeComparator().reversed();
        return "redirect:/"+user.getRole();
    }

    @GetMapping("/customer")
    String customer(Model model){
        model.addAttribute("user",user);
        Collections.sort(list, comparator);
        model.addAttribute("events", list);
        return "customer";
    }
    @GetMapping("/byNewDown")
    String filterNewDown(){
        MainController.comparator = new NewnessComparator();
        return "redirect:/"+user.getRole();
    }

    @GetMapping("/byNewUp")
    String filterNewUp(){
        MainController.comparator = new NewnessComparator().reversed();
        return "redirect:/"+user.getRole();
    }
    @GetMapping("/reload")
    String reload(){
        reset();
        return "redirect:/"+user.getRole();
    }

    @GetMapping("/owner")
    String ownerMain(Model model) {
        model.addAttribute("statuses",statisticMapper.statusList());
        model.addAttribute("events", list);
        model.addAttribute("sum", statisticMapper.getSumForYear());
        System.err.println(statisticMapper.getFavourite().getNumber());
        model.addAttribute("area",statisticMapper.getFavourite());
        return "owner";
    }

    @GetMapping("/myProfile")
    String goToMyProfile(@ModelAttribute("user") User user){
        if(user!=null) return "redirect:"+user.getRole();
        else return "redirect:/logIn";
    }

    @GetMapping("/logOut")
    String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        user = null;
        return "redirect:/";
    }

    @PostMapping("/pay")
    String getPay(@RequestParam int id){
        System.err.println(id);
        new EventMapper().updateStatus(id,statusMapper.getLastStatus().getLevel());
        reset();
        return "redirect:/customer";
    }

    @PostMapping("/addUser")
    String register(Model model, @ModelAttribute("user") User user, @RequestParam String name, @RequestParam(required=false) String surname, @RequestParam long phone, @RequestParam String login, @RequestParam String password){
        if(user!=null){
        if(user.getRole() == "admin"){
                if(userMapper.saveEntity(new Admin(name,surname,phone,login,password))==true)
                    return "redirect:/admin";
                else return "registration";
            }
        }

        if(new UserMapper().saveEntity(new Customer(name,surname,phone,login,password))==true)
            return "authorization";
        else {
            model.addAttribute("Error","Во время регистрации что-то пошло не так");
            return "registration";
        }
    }

    @PostMapping("/schedule")
    String getSchedule(Model model, @RequestParam int number){
        List<Event> list = eventMapper.getSchedule(number);
        Collections.sort(list,new TimeComparator());
        model.addAttribute("events", list);
        model.addAttribute("area",number);
        return "schedule";
    }

    @PostMapping("/addEvent")
    String addEvent(@ModelAttribute("user") User user,Model model, @RequestParam int area, @RequestParam int month,
                    @RequestParam int day, @RequestParam int hourFrom, @RequestParam int minuteFrom,
                    @RequestParam int hourTo, @RequestParam int minuteTo, @RequestParam int numberOfClients) throws SQLException {
        if( user!=null && user.getRole()=="customer" && eventMapper.saveEntity(new Event(numberOfClients,statusMapper.getStatusById(1),
                new Timestamp(LocalDateTime.now().getYear()-1900, month-1, day, hourFrom, minuteFrom, 0, 0),
                new Timestamp(LocalDateTime.now().getYear()-1900, month-1, day, hourTo, minuteTo, 0, 0),
                user,
                areaMapper.getEntityById(area))))
        {

        return "redirect:/";
        }
        else{
            model.addAttribute("err","Not correct time or customer");
            return "schedule";
        }
    }
}
