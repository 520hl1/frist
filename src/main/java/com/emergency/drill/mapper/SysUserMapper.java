package com.emergency.drill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emergency.drill.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    // 可添加自定义SQL方法
    //SysUser selectByUsername(@Param("username") String username);
}