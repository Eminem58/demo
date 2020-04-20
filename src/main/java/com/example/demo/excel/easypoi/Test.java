package com.example.demo.excel.easypoi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2019/12/29
 */
@RestController
@RequestMapping("/excel")
public class Test {
    /**
     * 导出
     *
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<User> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User personVo = new User();
            personVo.setUserName("张三" + i);
            personVo.setPassWord("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personList.add(personVo);
        }
        ExcelUtils.exportExcel(personList, "员工信息表", "员工信息", User.class, "员工信息", response);
    }
}
