package com.xiaowang.mesqle.controller;

import com.xiaowang.mesqle.pojo.SCM;
import com.xiaowang.mesqle.pojo.Sale;
import com.xiaowang.mesqle.service.SaleService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 销售模块Controller
 */
@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private CustController custController;
    /**
     * 查询全部销售订单
     */
    @GetMapping("/findSaleAll")
    public String findSaleAll(Model model, HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        List<SCM> slist = null;
        try {
            slist = this.saleService.findSaleAll();
            System.out.println("hhhhhhhhhhhhhhhhhhhhh"+slist.get(0).getSdate());
            model.addAttribute("sl",slist);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if(role==0){
            return "listSale";
        }else if(role==1){
            return "listSale1";
        }
        return "error";
    }
    /**
     * 预更新销售订单查询
     */
    @GetMapping("/preUpdateSale")
    public String preUpdate(int id,Model model, HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        try{
            Sale sale = this.saleService.findSaleById(id);
            model.addAttribute("sale",sale);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if(role==0){
            return "updateSale";
        }else if(role==1){
            return "updateSale1";
        }
        return "error";
    }
    /**
     * 更新销售订单
     */
    @PostMapping("/updateSale")
    public String updateSale(Sale sale){
        this.saleService.modifySale(sale);
        return "redirect:/sale/findSaleAll";
    }
    /**
     * 删除销售订单
     */
    @GetMapping("/delSale")
    public String delSale(int id){
        this.saleService.delSale(id);
        return "redirect:/sale/findSaleAll";
    }

    /**
     * 根据条件查询销售订单信息
     * @param model
     * @param cname
     * @param mname
     * @param date1
     * @param date2
     * @return
     */
    @PostMapping("/findSale")
    public String findSale(Model model, String cname, String mname, String date1,String date2, HttpServletRequest request) throws Exception{
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        List<SCM> slist = null;
        try {
            slist = this.saleService.findSale(cname,mname,date1,date2);
            model.addAttribute("sl",slist);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if(role==0){
            return "listSale";
        }else if(role==1){
            return "listSale1";
        }
        return "error";
    }
    /**
     * 添加销售订单
     */
    @PostMapping("/addSale")
    public String addSale(Sale sale){
        try {
            if(custController.isNewCust(sale.getCid())){
                return "custNotExist";
            }
            this.saleService.addSale(sale);
            //System.out.println(sale.getSdate());
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/sale/findSaleAll";
    }
    @GetMapping("/exReport")
    public String exReport(HttpServletResponse response,HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        XSSFWorkbook wb = this.saleService.exReport();
        String fileName = "Sales.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);

            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(role==0){
            return "ok";
        }else if(role==1){
            return "ok1";
        }
        return "error";
    }
}
