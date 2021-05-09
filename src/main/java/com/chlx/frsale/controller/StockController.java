package com.chlx.frsale.controller;

import com.chlx.frsale.pojo.CMP;
import com.chlx.frsale.pojo.MS;
import com.chlx.frsale.pojo.SCM;
import com.chlx.frsale.service.PurchaseService;
import com.chlx.frsale.service.SaleService;
import com.chlx.frsale.service.StockService;
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
import java.util.List;

/**
 * 库房管理模块
 */
@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private PurchaseService purchaseService;
    /**
     * 库存列表
     * @param model
     * @return
     */
    @GetMapping("/listStock")
    public String listStock(Model model, HttpServletRequest request){

        List<MS> mslist = this.stockService.listStock();
        model.addAttribute("mslist",mslist);
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        if(role==0){
            return "listStock";
        }else if(role==1){
            return "listStock1";
        }else if(role==2){
            return "listStock2";
        }else if(role==3){
            return "listStock3";
        }
        return "error";
    }

    /**
     * 通过药品名称进行查询
     * @param mname
     * @param model
     * @return
     */
    @PostMapping("/findStock")
    public String findStockByMname(String mname,Model model,HttpServletRequest request){
        List<MS> mslist = this.stockService.findStockByMname(mname);
        model.addAttribute("mslist",mslist);
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        if(role==0){
            return "listStock";
        }else if(role==1){
            return "listStock1";
        }else if(role==2){
            return "listStock2";
        }else if(role==3){
            return "listStock3";
        }
        return "error";
    }
    /**
     * 通过销售订单状态进行查询
     * @param model
     * @return
     */
    @GetMapping("/outStock")
    public String outStock(Model model,HttpServletRequest request){
        List<SCM> stocklist = null;
        try {
            stocklist = this.saleService.findSaleByFlag();
            model.addAttribute("stocklist",stocklist);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        if(role==0){
            return "outStock";
        }else if(role==3){
            return "outStock3";
        }
        return "error";
    }

    /**
     * 通过采购订单状态进行查询
     * @param model
     * @return
     */
    @GetMapping("/putStock")
    public String putStock(Model model,HttpServletRequest request){
        List<CMP> stocklist2 = null;
        try {
            stocklist2 = this.purchaseService.findPurchaseByFlag();
            model.addAttribute("stocklist2",stocklist2);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        if(role==0){
            return "putStock";
        }else if(role==3){
            return "putStock3";
        }
        return "error";
    }
    @GetMapping("/exReport")
    public String exReport(HttpServletResponse response,HttpServletRequest request){
        HttpSession session= request.getSession();
        int role = (int)session.getAttribute("type");
        XSSFWorkbook wb = this.stockService.exReport();
        String fileName = "库存报表.xlsx";
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
        } else if(role==2){
            return "ok2";
        }else if(role==3){
            return "ok3";
        }
        return "ok";
    }
    @GetMapping("/fahuo")
    public String fahuo(int id){
        this.stockService.fahuo(id);
        return "redirect:/stock/outStock";
    }
    @GetMapping("/ruku")
    public String ruku(int id){
        this.stockService.ruku(id);
        return "redirect:/stock/putStock";
    }
}
