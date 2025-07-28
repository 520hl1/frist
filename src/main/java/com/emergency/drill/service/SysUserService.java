package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {
    // 自定义业务方法
    SysUser getByUsername(String username);
    List<SysUser> listByDeptId(Integer deptId);
    List<SysUser> listByRole(String role);
}