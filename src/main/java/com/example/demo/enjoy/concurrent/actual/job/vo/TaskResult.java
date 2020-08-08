package com.example.demo.enjoy.concurrent.actual.job.vo;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/7
 */
public class TaskResult<R> {
    private final TaskResultType resultType;
    private final R resultValue;
    private final String reason;

    public TaskResult(TaskResultType resultType, R resultValue, String reason) {
        this.resultType = resultType;
        this.resultValue = resultValue;
        this.reason = reason;
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public R getResultValue() {
        return resultValue;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "TaskResult [resultType=" + resultType
                + ", returnValue=" + resultValue
                + ", reason=" + reason + "]";
    }
}
