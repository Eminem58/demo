package com.example.demo.enjoy.concurrent.actual.question;

/**
 * [题目实体类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class QuestionInDBVo {
    //题目id
    private final int id;
    //题目详情
    private final String detail;
    //题目sha摘要
    private final String sha;

    public QuestionInDBVo(int id, String detail, String sha) {
        this.id = id;
        this.detail = detail;
        this.sha = sha;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSha() {
        return sha;
    }
}
