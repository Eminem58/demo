package com.example.demo.enjoy.concurrent.actual.question.test;

import com.example.demo.enjoy.concurrent.actual.question.core.ProduceDocService;
import com.example.demo.enjoy.concurrent.actual.question.util.CreatePendingDocs;
import com.example.demo.enjoy.concurrent.actual.question.util.SL_QuestionBank;
import com.example.demo.enjoy.concurrent.actual.question.vo.SrcDocVo;

import java.util.List;

/**
 * [单web实现]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class SingleWeb {
    public static void main(String[] args) {
        System.out.println("题库开始初始化...........");
        SL_QuestionBank.initBank();
        System.out.println("题库初始化完成。");

        List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(3);
        long startTotal = System.currentTimeMillis();
        for(SrcDocVo doc:docList){
            System.out.println("["+doc.getDocName()+"]共有题目："
                    +doc.getQuestionList().size()+"个");
            System.out.println("开始处理文档："+doc.getDocName()+".......");
            long start = System.currentTimeMillis();
            String localName = ProduceDocService.makeDoc(doc);
            System.out.println("文档"+localName+"生成耗时："+(System.currentTimeMillis()-start)+"ms");
            start = System.currentTimeMillis();
            String remoteUrl = ProduceDocService.uploadDoc(localName);
            System.out.println("已上传至["+remoteUrl+"]耗时："+(System.currentTimeMillis()-start)+"ms");
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }
}
