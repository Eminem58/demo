package com.example.demo;

import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 〈测试工作流〉
 *
 * @author jinbiao
 * @create 2019/5/16
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaveActivitiTest {

    ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();

    //创建表
    @Test
    public void creatTable(){
        ProcessEngine processEngine=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
    }

    //发布流程
    @Test
    public void deployProcess(){
        RepositoryService repositoryService=processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder=repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("leave.bpmn");
        deploymentBuilder.deploy();
    }

    //启动流程
    @Test
    public void startProcess(){
        RuntimeService runtimeService=processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("myProcess_1");
    }

    /**
     * 根据名称查询流程部署
     */
    @Test
    public void testQueryDeploymentByName(){
        List<Deployment> deployments = processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymenTime()//按照部署时间排序
                .desc()//按照降序排序
                .deploymentName("请假流程")
                .list();
        for (Deployment deployment : deployments) {
            System.out.println(deployment.getId());
        }
    }
    /**
     * 查询所有的部署流程
     */
    @Test
    public void queryAllDeplyoment(){
        //得到流程引擎
        List<Deployment> lists = processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymenTime()//按照部署时间排序
                .desc()//按照降序排序
                .list();
        for (Deployment deployment:lists) {
            System.out.println(deployment.getId() +"    部署名称" + deployment.getName());
        }
    }
    /**
     * 查询所有的流程定义
     */
    @Test
    public void testQueryAllPD(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> pdList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition pd : pdList) {
            System.out.println(pd.getId());
        }
    }
    /**
     * 查看流程图
     * 根据deploymentId和name(在act_ge_bytearray数据表中)
     */
    @Test
    public void testShowImage() throws Exception{
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = processEngine.getRepositoryService()
                /**
                 * deploymentID
                 * 文件的名称和路径
                 */
                .getResourceAsStream("801","shenqing.png");
        OutputStream outputStream3 = new FileOutputStream("e:/processimg.png");
        int b = -1 ;
        while ((b=inputStream.read())!=-1){
            outputStream3.write(b);
        }
        inputStream.close();
        outputStream3.close();
    }
    //查看任务
    @Test
    public void queryTask(){
        TaskService taskService=processEngine.getTaskService();
        List<Task> tasks=taskService.createTaskQuery().taskId("_4").list();
        for (Task task:tasks) {
            System.out.println("taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
        }
    }

    //办理任务
    @Test
    public void handleTask(){
        TaskService taskService=processEngine.getTaskService();
        taskService.complete("2505");
    }

    //办理任务
    @Test
    public void getSequenceFlow(){
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition("myProcess_1:1:4");
        processEngine.getRuntimeService().startProcessInstanceById("qingjia","");

    }


}
