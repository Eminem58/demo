package com.example.demo.enjoy.concurrent.actual.question;

/**
 * [题目保存在缓存中的实体]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class QuestionInCacheVo {
    private final String questionDetail;
    private final String questionSha;

    public QuestionInCacheVo(String questionDetail, String questionSha) {
        this.questionDetail = questionDetail;
        this.questionSha = questionSha;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public String getQuestionSha() {
        return questionSha;
    }
}
