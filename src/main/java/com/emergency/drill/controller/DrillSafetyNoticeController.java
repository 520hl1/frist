package com.emergency.drill.controller;

import com.emergency.drill.entity.DrillSafetyNotice;
import com.emergency.drill.service.DrillSafetyNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/drillSafetyNotice")
public class DrillSafetyNoticeController {

    @Autowired
    private DrillSafetyNoticeService drillSafetyNoticeService;

    // 新增接口：处理文件上传和表单数据
    @PostMapping("/add")
    public String addDrillSafetyNotice(
            @RequestParam("noticeTitle") String noticeTitle,
            @RequestParam("noticeContent") String noticeContent,
            @RequestParam(value = "voiceFile", required = false) MultipartFile voiceFile,
            @RequestParam(value = "duration", required = false, defaultValue = "60") Integer duration,
            @RequestParam("isActive") Integer isActive,
            @RequestParam("createBy") String createBy
    ) throws IOException {
        // 创建实体类对象
        DrillSafetyNotice notice = new DrillSafetyNotice();
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setDuration(duration);
        notice.setIsActive(isActive);
        notice.setCreateBy(createBy);

        // 处理文件上传
        if (voiceFile != null && !voiceFile.isEmpty()) {
            // 示例：保存文件到本地路径（需根据实际情况修改）
            String filePath = "upload/safety_notice/" + voiceFile.getOriginalFilename();
            voiceFile.transferTo(new java.io.File(filePath)); // 实际项目中建议使用文件存储工具类
            notice.setVoiceFile(filePath);
        }

        drillSafetyNoticeService.save(notice);
        return "添加成功";
    }

    // 更新接口：处理文件上传和表单数据
    @PutMapping("/update")
    public String updateDrillSafetyNotice(
            @RequestParam("id") Integer id,
            @RequestParam("noticeTitle") String noticeTitle,
            @RequestParam("noticeContent") String noticeContent,
            @RequestParam(value = "voiceFile", required = false) MultipartFile voiceFile,
            @RequestParam(value = "duration", required = false, defaultValue = "60") Integer duration,
            @RequestParam("isActive") Integer isActive,
            @RequestParam("createBy") String createBy
    ) throws IOException {
        // 查询原有数据
        DrillSafetyNotice originalNotice = drillSafetyNoticeService.getById(id);
        if (originalNotice == null) {
            return "数据不存在";
        }

        // 更新非文件字段
        originalNotice.setNoticeTitle(noticeTitle);
        originalNotice.setNoticeContent(noticeContent);
        originalNotice.setDuration(duration);
        originalNotice.setIsActive(isActive);
        originalNotice.setCreateBy(createBy);

        // 处理文件上传（若有新文件则覆盖原有路径）
        if (voiceFile != null && !voiceFile.isEmpty()) {
            String filePath = "upload/safety_notice/" + voiceFile.getOriginalFilename();
            voiceFile.transferTo(new java.io.File(filePath));
            originalNotice.setVoiceFile(filePath);
        }

        drillSafetyNoticeService.updateById(originalNotice);
        return "更新成功";
    }

    // 其他接口（删除、查询）保持不变
    @DeleteMapping("/delete/{id}")
    public String deleteDrillSafetyNotice(@PathVariable Integer id) {
        drillSafetyNoticeService.removeById(id);
        return "删除成功";
    }

    @GetMapping("/get/{id}")
    public DrillSafetyNotice getDrillSafetyNotice(@PathVariable Integer id) {
        return drillSafetyNoticeService.getById(id);
    }

    @GetMapping("/list")
    public List<DrillSafetyNotice> listDrillSafetyNotices() {
        return drillSafetyNoticeService.list();
    }
}