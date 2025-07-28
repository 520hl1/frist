package com.emergency.drill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 注意是 @Controller，不是 @RestController
public class PageController {

    // 访问登录页
    @GetMapping("/login.html")
    public String loginPage() {
        return "login"; // 对应 templates/login.html
    }

    // 访问主页
    @GetMapping("/index.html")
    public String indexPage() {
        return "index"; // 对应 templates/index.html
    }

    // 访问演练计划管理页
    @GetMapping("/drill-plan.html")
    public String drillPlanPage() {
        return "drill-plan"; // 对应 templates/drill-plan.html
    }

    // 访问应急预案管理页
    @GetMapping("/emergency-plan.html")
    public String emergencyPlanPage() {
        return "emergency-plan"; // 对应 templates/emergency-plan.html
    }

    // 其他页面对应的方法...
    @GetMapping("/drill-satetynotice.html")
    public String drillSatetyNoticePage() {
        return "drill-satetynotice";
    }

    @GetMapping("/drill-task.html")
    public String drillTaskPage() {
        return "drill-task";
    }

    @GetMapping("/sysdepartment.html")
    public String sysDepartmentPage() {
        return "sysdepartment";
    }

    @GetMapping("/sysuer.html")
    public String sysUserPage() {
        return "sysuer";
    }

    @GetMapping("/drill-evaluation.html")
    public String drillEvaluationPage() {
        return "drill-evaluation";
    }

    @GetMapping("/drill-project.html")
    public String drillProjectPage() {
        return "drill-project";
    }

    @GetMapping("/drill-task-log.html")
    public String drillTasklogPage() {
        return "drill-task-log";
    }
}