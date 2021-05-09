package com.chlx.frsale.controller;
import com.chlx.frsale.pojo.*;
import com.chlx.frsale.service.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private CustService custService;

    /**
     * 添加采购订单，采购人员拥有的功能
     * @param purchase
     * @return
     */
    @PostMapping("/addPurchase")
    public String addPurchase(Purchase purchase, HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        //判断药品和客户是否已存在
        Medicine medicine = this.medicineService.findMedicineById(purchase.getMid());
        System.out.println("采购的药品名称是：" + medicine.getMname());
        Customer customer = this.custService.findCustById(purchase.getCid());
        System.out.println("采购的客户名称是：" + customer.getCname());
        if(medicine.getMname() == null){
            if(role==0){
                return "addNewMedicine";
            }else if(role==2){
                return "addNewMedicine2";
            }

        }else {
            if (customer.getCname() == null) {
                if(role==0){
                    return "addCust";
                }else if(role==2){
                    return "addCust2";
                }

            }else {
                purchase.setPflag(0);//采购员增加订单只能增加未入库的订单
                System.out.println(purchase.getPdate());
                try {
                    this.purchaseService.addPurchase(purchase);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "error";
                }
                return "redirect:/purchase/findAllPurchase";
            }
        }
        return "error";
    }

    /**
     * 展示所有采购订单列表
     * @param model
     * @return
     */
    @GetMapping("/findAllPurchase")
    public String findAllPurchase(Model model,HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        List<CMP> cmplist = new ArrayList<CMP>();
        try{
            List<Purchase> list = this.purchaseService.queryAllPurchase();
            for (int i=0; i < list.size(); i ++){
                CMP cmp = new CMP();
                cmp.setPurchase(list.get(i));
                cmp.setCustomer(this.custService.findCustById(list.get(i).getCid()));
                cmp.setMedicine(this.medicineService.findMedicineById(list.get(i).getMid()));
                cmplist.add(cmp);
                System.out.println(cmp.toString());
            }
            System.out.println("cmplist.size()==" + cmplist.size());
            model.addAttribute("cmplist", cmplist);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if(role==0){
            return "showPurchase";
        }else if(role==2){
            return "showPurchase2";
        }
        return "error";
    }

    @GetMapping("/updatePurchase")
    public String goToUpdatePurchase(Model model, int id,HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        try{
            Purchase purchase = this.purchaseService.queryPurchaseById(id);
            model.addAttribute("purchase", purchase);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if(role==0){
            return "updatePurchaseInfo";
        }else if(role==2){
            return "updatePurchaseInfo2";
        }
        return "error";
    }

    /**
     * 修改订单状态，应当只有仓库管理员才可以修改
     * @param id
     * @return
     */
    @GetMapping("/updatePurchaseFlag")
    public String updatePurchaseFlag(int id){
        Purchase purchase = new Purchase();
        purchase.setPid(id);
        try{
            this.purchaseService.modifyPurchaseFlag(purchase);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/purchase/findAllPurchase";
    }

    /**
     * 修改采购订单信息
     * @param purchase
     * @return
     */
    @PostMapping("/updatePurchaseInfo")
    public String updatePurchaseInfo(Purchase purchase){
        try{
            this.purchaseService.modifyPurchaseInfo(purchase);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/purchase/findAllPurchase";
    }

    /**
     * 按条件查询
     * @param model
     * @param cname
     * @param mname
     * @param date1
     * @param date2
     * @return
     */
    @PostMapping("/selectPurByAll")
    public String selectPurchaseByAll(Model model, String cname, String mname, String date1, String date2,HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        System.out.println("查询条件有：" + cname + ", " + mname + ", " + date1 + ", " + date2);
        List<CMP> cmplist = new ArrayList<CMP>();
        try{
            List<Purchase> list = this.purchaseService.queryPurchaseByAll(cname, mname, date1, date2);
            for (int i=0; i < list.size(); i ++){
                CMP cmp = new CMP();
                cmp.setPurchase(list.get(i));
                cmp.setCustomer(this.custService.findCustById(list.get(i).getCid()));
                cmp.setMedicine(this.medicineService.findMedicineById(list.get(i).getMid()));
                cmplist.add(cmp);
                System.out.println(cmp.toString());
            }
            System.out.println("cmplist.size()==" + cmplist.size());
            model.addAttribute("cmplist", cmplist);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if(role==0){
            return "showPurchase";
        }else if(role==2){
            return "showPurchase2";
        }
        return "error";
    }

    /**
     * 生成报表
     * @param response
     * @return
     */
    @GetMapping("/exReport")
    public String exReport(HttpServletResponse response,HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        List<CMP> cmplist = new ArrayList<CMP>();
        try{
            List<Purchase> list = this.purchaseService.queryAllPurchase();
            for (int i=0; i < list.size(); i ++){
                CMP cmp = new CMP();
                cmp.setPurchase(list.get(i));
                cmp.setCustomer(this.custService.findCustById(list.get(i).getCid()));
                cmp.setMedicine(this.medicineService.findMedicineById(list.get(i).getMid()));
                cmplist.add(cmp);
                System.out.println(cmp.toString());
            }
            System.out.println("cmplist.size()==" + cmplist.size());

            XSSFWorkbook wb = this.purchaseService.exReport(cmplist);
            String fileName = "Purchases报表.xlsx";
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream outputStream =null;
            try {
                fileName = URLEncoder.encode(fileName,"UTF-8");
                outputStream = response.getOutputStream();
                wb.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(role==0){
            return "ok";
        }else if(role==2){
            return "ok2";
        }
        return "error";
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @GetMapping("deletePurchase")
    public String deletePurchase(int id){
        try{
            this.purchaseService.dropPurchaseById(id);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/purchase/findAllPurchase";
    }
}
