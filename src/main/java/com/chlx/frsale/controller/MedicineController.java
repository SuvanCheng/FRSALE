package com.chlx.frsale.controller;
import com.chlx.frsale.pojo.Medicine;
import com.chlx.frsale.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @PostMapping("/addMedicine")
    public String addMedicine(Medicine medicine, HttpServletRequest request){
        try{
            this.medicineService.addMedicine(medicine);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        if(role==0){
            return "/addNewMedicine";
        }else if(role==2){
            return "/addNewMedicine2";
        }
        return "error";
    }
}

