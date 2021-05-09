package com.chlx.frsale.controller;

import com.xiaowang.mesqle.pojo.Customer;
import com.xiaowang.mesqle.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Cust")
public class CustController {

    @Autowired
    private CustService custService;

    /**
     * 添加客户
     * @param customer
     * @return
     */
    @PostMapping("/addCust")
    public String addCust(Customer customer,HttpServletRequest request){
        try {
            this.custService.addCust(customer);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        if(role==0){
            return "redirect:/ok";
        }else if(role==1){
            return "addCust1";
        }else if(role==2){
            return "addCust2";
        }
        return "error";

    }
    /**
     * 判断是否是新客户
     */
    @GetMapping("/isNewCust")
    public boolean isNewCust(int cid){
        Customer customer = new Customer();
        try {
            customer = this.custService.findCustById(cid);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(customer.getCname()==null){
            return true;
        }
        if(!(customer.getCname()==null)){
            return false;
        }
        return true;
    }
}
