package com.example.demo.enjoy.concurrent.actual.question.core;

import com.example.demo.enjoy.concurrent.actual.question.util.SL_QuestionBank;

/**
 * [模拟解析题目文本，下载图片等操作，返回解析后的文本]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class SingleQstService {
    public static String makeQuestion(Integer questionId) {
        return QstService.makeQuestion(questionId, SL_QuestionBank.getQuestion(questionId).getDetail());
    }
}
