package com.example.demo.enjoy.concurrent.actual.question;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * [生成待处理文档]
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/8
 */
public class CreatePendingDocs {
    /**
     * 生成一批待处理的文档
     */
    public static List<SrcDocVo> makePendingDoc(int count) {
        Random random = new Random();
        List<SrcDocVo> docVoList = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> questionList = new LinkedList<>();
            for (int j = 0; j < random.nextInt(Constant.QUESTION_COUNT_IN_DOC); j++) {
                questionList.add(random.nextInt(Constant.SIZE_OF_QUESTION_BANK));
            }
            docVoList.add(new SrcDocVo("doc_" + i, questionList));
        }
        return docVoList;
    }

    ;


}
