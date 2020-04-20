package com.example.demo.excel.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2019/12/29
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    /**
     * 去打印界面
     * @author: lemon
     * @since: 2019/11/1 0001
     */
    @RequestMapping("/")
    public String toExcel(){

        return "views/makeExcel";
    }

    /**
     * 导出excel表
     * @author lemon
     * @since 2019/11/1 0001
     */
    @RequestMapping("/makeExcel")
    public void makeExcel(HttpServletResponse response) throws Exception {

        //打印Excel表
        ExcelUtils.exportExcel(response, "文件名.xlsx", excelService.getOutputExcelProjectData());

    }
}
