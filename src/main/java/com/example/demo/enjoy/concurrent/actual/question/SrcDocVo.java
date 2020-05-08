package com.example.demo.enjoy.concurrent.actual.question;

import java.util.List;

/**
 * [待处理文档实体类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class SrcDocVo {
    private final String docName;
    private final List<Integer> questionList;

    public SrcDocVo(String docName, List<Integer> questionList) {
        this.docName = docName;
        this.questionList = questionList;
    }

    public String getDocName() {
        return docName;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }
}
