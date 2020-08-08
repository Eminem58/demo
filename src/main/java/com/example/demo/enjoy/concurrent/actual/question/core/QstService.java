package com.example.demo.enjoy.concurrent.actual.question.core;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * [单个题目处理的服务类]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class QstService {
    @SneakyThrows
    public static String makeQuestion(Integer questionId, String questionSrc) {
        Thread.sleep(new Random().nextInt(500));
        return "Question[id=" + questionId + ", content=" + questionSrc + "]";
    }
}
