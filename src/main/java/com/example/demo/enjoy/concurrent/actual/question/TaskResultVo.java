package com.example.demo.enjoy.concurrent.actual.question;

import java.util.concurrent.Future;

/**
 * [并发处理返回的题目结果实体类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class TaskResultVo {
    private final String questionDetail;
    private final Future<QuestionInCacheVo> questionFuture;

    public TaskResultVo(String questionDetail) {
        this.questionDetail = questionDetail;
        this.questionFuture =null;
    }

    public TaskResultVo(Future<QuestionInCacheVo> questionFuture) {
        this.questionFuture = questionFuture;
        this.questionDetail = null;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public Future<QuestionInCacheVo> getQuestionFuture() {
        return questionFuture;
    }
}
