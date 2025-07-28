package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.SysUser;
import com.emergency.drill.mapper.SysUserMapper;
import com.emergency.drill.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getByUsername(String username) {
        // 使用 MyBatis-Plus 内置方法而非自定义方法
        return getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public List<SysUser> listByDeptId(Integer deptId) {
        return list(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getDeptId, deptId));
    }

    @Override
    public List<SysUser> listByRole(String role) {
        return list(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getRole, role));
    }
}