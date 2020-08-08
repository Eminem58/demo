package com.example.demo.enjoy.concurrent.actual.question.util;

import com.example.demo.enjoy.concurrent.actual.question.constant.Constant;
import com.example.demo.enjoy.concurrent.actual.question.vo.QuestionInDBVo;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * [模拟存储中数据库中的题库数据]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class SL_QuestionBank {
    /**
     * 题库数据存储
     */
    private static ConcurrentHashMap<Integer, QuestionInDBVo> questionBankMap = new ConcurrentHashMap<>();
    /**
     * 定时任务池，负责定时更新题库数据
     */
    private static ScheduledExecutorService updateQuestionBank = new ScheduledThreadPoolExecutor(1);

    /**
     * 生成随机字符串，代表题目的实际内容,length表示题目的长度
     */
    private static String makeQuestionDetail(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();
    }

    /**
     * 获得题目，我们假设一次数据库的读耗时在一般在20ms左右，所以休眠20ms
     */
    @SneakyThrows
    public static QuestionInDBVo getQuestion(int questionId) {
        Thread.sleep(20);
        return questionBankMap.get(questionId);
    }

    /**
     * 初始化题库
     */
    public static void initBank() {
        for (int i = 0; i < Constant.SIZE_OF_QUESTION_BANK; i++) {
            String questionContent = makeQuestionDetail(800);
            questionBankMap.put(i, new QuestionInDBVo(i, questionContent,
                    EncryptUtils.EncryptBySHA1(questionContent)));
        }
        updateQuestionTimer();
    }

    /**
     * 更新题库的定时任务
     */
    private static class UpdateBank implements Runnable{

        @Override
        public void run() {
            Random random = new Random();
            int questionId = random.nextInt(Constant.SIZE_OF_QUESTION_BANK);
            String questionContent = makeQuestionDetail(700);
            questionBankMap.put(questionId,new QuestionInDBVo(questionId,
                    questionContent,EncryptUtils.EncryptBySHA1(questionContent)));
            //System.out.println("题目【"+questionId+"】被更新！！");
        }
    }

    /**
     * 定期更新题库数据
     */
    private static void updateQuestionTimer() {
        System.out.println("开始定时更新题库..........................");
        updateQuestionBank.scheduleAtFixedRate(new UpdateBank(),
                15, 5, TimeUnit.SECONDS);
    }
    //获得题目Sha值，我们假设一次数据库的读耗时在一般在10ms左右，所以休眠10ms
    @SneakyThrows
    public static String getQuestionSha(int i) {
        Thread.sleep(20);
        return questionBankMap.get(i).getSha();
    }
}
