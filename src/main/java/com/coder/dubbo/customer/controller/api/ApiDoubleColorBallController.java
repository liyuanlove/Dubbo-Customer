package com.coder.dubbo.customer.controller.api;

import com.coder.springbootdomecollection.model.DoubleColorBall;
import com.coder.springbootdomecollection.service.DoubleColorBallService;
import com.coder.util.ConstUtils;
import com.coder.util.DateUtils;
import com.coder.util.StringUtils;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.coder.util.ExcelUtils;

@RestController
@RequestMapping("/api/doublecolorball")
@Scope("prototype")
public class ApiDoubleColorBallController {

    @Autowired
    private DoubleColorBallService doubleColorBallService;

    private DoubleColorBall doubleColorBall;

    private static final String RETURN_DOUBLE_COLOR_BALL = "returndoublecolorball.queue";
    private static final String INSERT_DOUBLE_COLOR_BALL = "insertdoublecolorball.queue";
    private static final String UPDATE_DOUBLE_COLOR_BALL = "updatedoublecolorball.queue";

    @JmsListener(destination=RETURN_DOUBLE_COLOR_BALL,containerFactory = "queueListenerFactory")
    public void consumerMessage(String text){
        System.out.println(text);
    }


    @PostMapping("/excel")
    public String addDoubleColorBall(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        Workbook workBook = ExcelUtils.openWorkbookForRead(file,fileName);
        if(ExcelUtils.isExcel(workBook)){
            Sheet sheet = workBook.getSheetAt(0);
            List<DoubleColorBall> doubleColorBalls = new ArrayList<>();
            for(int i=3,totalRowNum=sheet.getLastRowNum(); i<=totalRowNum; i++){
                Row row = sheet.getRow(i);
                if(row != null){
                    DoubleColorBall doubleColorBall = new DoubleColorBall();
                    String dateStr = String.valueOf(ExcelUtils.getCellValue(row.getCell((short)1)));
                    Date createDate = DateUtils.parse(dateStr,ConstUtils.DATAPATTERN1);
                    Integer id = Integer.valueOf(String.valueOf(ExcelUtils.getCellValue(row.getCell((short)2))));
                    String ballStr = String.valueOf(ExcelUtils.getCellValue(row.getCell((short)3)));
                    if(!StringUtils.isNullOrEmpty(ballStr)){
                        String[] balls = ballStr.split(" ");
                        if(balls.length >= 7){
                            doubleColorBall.setRedball1(Integer.valueOf(balls[0]));
                            doubleColorBall.setRedball2(Integer.valueOf(balls[1]));
                            doubleColorBall.setRedball3(Integer.valueOf(balls[2]));
                            doubleColorBall.setRedball4(Integer.valueOf(balls[3]));
                            doubleColorBall.setRedball5(Integer.valueOf(balls[4]));
                            doubleColorBall.setRedball6(Integer.valueOf(balls[5]));
                            doubleColorBall.setBlueball(Integer.valueOf(balls[6]));
                        }
                    }
                    doubleColorBall.setId(id);
                    doubleColorBall.setCreatedate(createDate);
                    doubleColorBalls.add(doubleColorBall);
                }
            }
            int id = doubleColorBallService.insertToBatch(doubleColorBalls);
            if(id > 0){
                return "导入成功";
            }else{
                return "导入出错";
            }
        }else{
            return "请上传Excel文件";
        }
    }

    @GetMapping("/list")
    public PageInfo<DoubleColorBall> List(
            @RequestParam("pageIndex") int pageIndex,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("orderBy") String orderBy,
            @RequestParam("sequnce") String sequnce){
        if(doubleColorBall == null){
            doubleColorBall = new DoubleColorBall();
        }
        if(!StringUtils.isNullOrEmpty(orderBy)){
            doubleColorBall.setOrderBy(orderBy);
        }
        if(!StringUtils.isNullOrEmpty(sequnce)){
            doubleColorBall.setOrderSequnce(sequnce);
        }
        PageInfo<DoubleColorBall> balls = doubleColorBallService.selectPage(pageIndex,pageSize,doubleColorBall);
        return balls;
    }
}
