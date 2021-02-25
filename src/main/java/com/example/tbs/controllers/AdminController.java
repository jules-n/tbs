package com.example.tbs.controllers;

import com.example.tbs.dataMapper.IAreaMapper;
import com.example.tbs.dataMapper.IEventMapper;
import com.example.tbs.dataMapper.IFacilityMapper;
import com.example.tbs.dataMapper.IStatusMapper;
import com.example.tbs.dataMapper.oracleMappers.*;
import com.example.tbs.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value = "user")
@Controller
public class AdminController{

    private IEventMapper eventMapper = new EventMapper();
    private IStatusMapper statusMapper = new StatusMapper();
    private IFacilityMapper facilityMapper = new FacilityMapper();
    private IAreaMapper areaMapper = new AreaMapper();

    @PostMapping("/changeStatus")
    String adminMain(Model model, @RequestParam int event) {
        //;
        Event toChangeEvent = eventMapper.getEntityById(event);
        model.addAttribute("event",toChangeEvent);
        model.addAttribute("statuses", statusMapper.getNextStatus(toChangeEvent.getStatus().getLevel()));
        return "changes";
    }

    @PostMapping("/save")
    String updateStatus(@RequestParam int event, @RequestParam int status){
        eventMapper.updateStatus(event,status);
        return "redirect:/admin";
    }

    @GetMapping("/addFacility")
    String getPageAddFacility(){
        return "facility";
    }

    @PostMapping("/addFacility")
    String addFacility(@RequestParam String name){
        facilityMapper.saveEntity(new Facility(name));
        return "redirect:/admin";
    }

    @GetMapping("/addArea")
    String getPageAddRoom(Model model){
        model.addAttribute("facilities", facilityMapper.getAll());
        return "area";
    }
    @PostMapping("/addArea")
    String addArea(@RequestParam int[] facilitiesInRoom, @RequestParam int number, @RequestParam String description){
        areaMapper.saveEntity(new Area(number,description,facilitiesInRoom));
        return "redirect:/admin";
    }

}
