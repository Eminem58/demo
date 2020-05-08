package com.example.demo.enjoy.concurrent.actual.question;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * [处理文档的服务]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class ProduceDocService {
    /**
     * 将待处理文档处理为本地实际文档
     */
    public static String makeDoc(SrcDocVo srcDocVo) {
        System.out.println("开始处理文档：" + srcDocVo.getDocName());

        StringBuffer sb = new StringBuffer();
        for (Integer questionId : srcDocVo.getQuestionList()) {
            sb.append(SingleQstService.makeQuestion(questionId));
        }
        return "complete-" + System.currentTimeMillis() + "-" + srcDocVo.getDocName() + "pdf";
    }
    /*异步生成题目*/
    public static String makeDocAsyn(SrcDocVo pendingDocVo) throws ExecutionException, InterruptedException {
        System.out.println("开始处理文档："+ pendingDocVo.getDocName());
        /*每个题目的处理结果*/
        Map<Integer,TaskResultVo> qstResultMap = new HashMap<>();
        for(Integer questionId:pendingDocVo.getQuestionList()){
            qstResultMap.put(questionId,
                    ParallelQstService.makeQuestion(questionId));
        }

        StringBuffer sb = new StringBuffer();
        for(Integer questionId:pendingDocVo.getQuestionList()){
            TaskResultVo taskResultVo = qstResultMap.get(questionId);
            sb.append(taskResultVo.getQuestionDetail()==null?
                    taskResultVo.getQuestionFuture().get().getQuestionDetail()
                    :taskResultVo.getQuestionDetail());

        }
        return "complete_"+System.currentTimeMillis()+"_"
                + pendingDocVo.getDocName()+".pdf";
    }
    /**
     * 上传文档到网络
     */
    @SneakyThrows
    public static String uploadDoc(String docName) {
        Thread.sleep(3000+new Random().nextInt(500));
        return "http://www.xxxx.com/file/upload/" + docName;
    }


}
