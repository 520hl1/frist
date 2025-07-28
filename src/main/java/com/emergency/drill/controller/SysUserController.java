package com.emergency.drill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.emergency.drill.entity.SysUser;
import com.emergency.drill.service.SysUserService;
import com.emergency.drill.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {
    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private SysUserService sysUserService;

    // 登录接口（使用 Integer 类型 ID）
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody SysUser user) {
        try {
            // 1. 根据用户名查询用户
            SysUser dbUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));

            // 2. 检查用户是否存在
            if (dbUser == null) {
                return response(false, "用户名不存在", null);
            }

            // 3. 验证密码（注意密码是否加密）
            if (!dbUser.getPassword().equals(user.getPassword())) {
                return response(false, "密码错误", null);
            }

            // 4. 生成Token并返回
            String token = JwtUtil.generateToken(dbUser.getId(), dbUser.getRole());
            Map<String, Object> result = new HashMap<>();
            result.put("token", token);

            // 避免返回敏感信息
            dbUser.setPassword(null);
            result.put("user", dbUser);

            return response(true, "登录成功", result);

        } catch (Exception e) {
            log.error("登录异常: {}", e.getMessage(), e);
            return response(false, "登录失败，请稍后重试", null);
        }
    }

    // 删除接口（路径参数为 Integer）
    @DeleteMapping("/delete/{id}")
    public String deleteSysUser(@PathVariable Integer id) { // 改回 Integer
        sysUserService.removeById(id);
        return "删除成功";
    }

    // 获取用户接口
    @GetMapping("/get/{id}")
    public SysUser getSysUser(@PathVariable Integer id) { // 改回 Integer
        return sysUserService.getById(id);
    }

    // 其他接口（新增、更新等）保持 Integer 类型不变
    @PostMapping("/add")
    public String addSysUser(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return "添加成功";
    }

    @PutMapping("/update")
    public String updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return "更新成功";
    }

    @GetMapping("/list")
    public java.util.List<SysUser> listSysUsers() {
        return sysUserService.list();
    }

    // 通用响应方法
    private Map<String, Object> response(boolean success, String message, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        result.put("data", data);
        return result;
    }
}