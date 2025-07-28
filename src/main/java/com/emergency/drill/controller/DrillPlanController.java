package com.emergency.drill.controller;

import com.emergency.drill.entity.DrillPlan;
import com.emergency.drill.service.DrillPlanService;
import com.emergency.drill.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/drillPlan")
public class DrillPlanController {

    @Autowired
    private DrillPlanService drillPlanService;

    private static final String UPLOAD_DIR = "D:/app/upload/";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    // 从请求头中解析Token获取用户ID
    private Integer getUserIdFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return JwtUtil.getUserId(token);
        }
        return null;
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, Object>> addDrillPlan(
            @RequestParam("projectId") Integer projectId,
            @RequestParam("planCode") String planCode,
            @RequestParam("deptId") Integer deptId,
            @RequestParam("drillReason") String drillReason,
            @RequestParam("drillContent") String drillContent,
            @RequestParam("drillPersons") String drillPersons,
            @RequestParam("impactScope") String impactScope,
            @RequestParam("cooperateDepts") String cooperateDepts,
            @RequestParam("drillTime") String drillTimeStr,
            @RequestParam("status") Integer status,
            @RequestParam(value = "drillPlanFile", required = false) MultipartFile file,
            HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();

        try {
            Integer userId = getUserIdFromToken(request);
            if (userId == null) {
                result.put("success", false);
                result.put("message", "未认证，请先登录");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }

            DrillPlan drillPlan = new DrillPlan();
            drillPlan.setProjectId(projectId);
            drillPlan.setPlanCode(planCode);
            drillPlan.setDeptId(deptId);
            drillPlan.setDrillReason(drillReason);
            drillPlan.setDrillContent(drillContent);
            drillPlan.setDrillPersons(drillPersons);
            drillPlan.setImpactScope(impactScope);
            drillPlan.setCooperateDepts(cooperateDepts);
            drillPlan.setDrillTime(DATE_FORMAT.parse(drillTimeStr));
            drillPlan.setStatus(status);
            drillPlan.setCreateBy(userId.toString());
            drillPlan.setCreateTime(new Date());
            drillPlan.setUpdateTime(new Date());

            if (file != null && !file.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                File dest = new File(UPLOAD_DIR + fileName);
                file.transferTo(dest);
                drillPlan.setDrillPlanFile(UPLOAD_DIR + fileName);
            }

            drillPlanService.save(drillPlan);
            result.put("success", true);
            result.put("message", "添加成功");
            return ResponseEntity.ok(result);
        } catch (ParseException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "日期格式错误: " + e.getMessage());
            return ResponseEntity.badRequest().body(result);
        } catch (IOException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "文件上传失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "添加失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PutMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, Object>> updateDrillPlan(
            @RequestParam("id") Integer id,
            @RequestParam("projectId") Integer projectId,
            @RequestParam("planCode") String planCode,
            @RequestParam("deptId") Integer deptId,
            @RequestParam("drillReason") String drillReason,
            @RequestParam("drillContent") String drillContent,
            @RequestParam("drillPersons") String drillPersons,
            @RequestParam("impactScope") String impactScope,
            @RequestParam("cooperateDepts") String cooperateDepts,
            @RequestParam("drillTime") String drillTimeStr,
            @RequestParam("status") Integer status,
            @RequestParam(value = "drillPlanFile", required = false) MultipartFile file,
            HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();

        try {
            DrillPlan drillPlan = drillPlanService.getById(id);
            if (drillPlan == null) {
                result.put("success", false);
                result.put("message", "计划不存在");
                return ResponseEntity.badRequest().body(result);
            }

            Integer userId = getUserIdFromToken(request);
            if (userId == null) {
                result.put("success", false);
                result.put("message", "未认证，请先登录");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }

            drillPlan.setProjectId(projectId);
            drillPlan.setPlanCode(planCode);
            drillPlan.setDeptId(deptId);
            drillPlan.setDrillReason(drillReason);
            drillPlan.setDrillContent(drillContent);
            drillPlan.setDrillPersons(drillPersons);
            drillPlan.setImpactScope(impactScope);
            drillPlan.setCooperateDepts(cooperateDepts);
            drillPlan.setDrillTime(DATE_FORMAT.parse(drillTimeStr));
            drillPlan.setStatus(status);
            drillPlan.setCreateBy(userId.toString());
            drillPlan.setUpdateTime(new Date());

            if (file != null && !file.isEmpty()) {
                if (drillPlan.getDrillPlanFile() != null) {
                    new File(drillPlan.getDrillPlanFile()).delete();
                }
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                File dest = new File(UPLOAD_DIR + fileName);
                file.transferTo(dest);
                drillPlan.setDrillPlanFile(UPLOAD_DIR + fileName);
            }

            drillPlanService.updateById(drillPlan);
            result.put("success", true);
            result.put("message", "更新成功");
            return ResponseEntity.ok(result);
        } catch (ParseException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "日期格式错误: " + e.getMessage());
            return ResponseEntity.badRequest().body(result);
        } catch (IOException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "文件上传失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteDrillPlan(@PathVariable Integer id, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        try {
            Integer userId = getUserIdFromToken(request);
            if (userId == null) {
                result.put("success", false);
                result.put("message", "未认证，请先登录");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }

            DrillPlan plan = drillPlanService.getById(id);
            if (plan == null) {
                result.put("success", false);
                result.put("message", "计划不存在");
                return ResponseEntity.badRequest().body(result);
            }

            if (plan.getDrillPlanFile() != null) {
                File file = new File(plan.getDrillPlanFile());
                if (file.exists()) file.delete();
            }

            drillPlanService.removeById(id);
            result.put("success", true);
            result.put("message", "删除成功");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @GetMapping("/get/{id}")
    public DrillPlan getDrillPlan(@PathVariable Integer id) {
        return drillPlanService.getById(id);
    }

    @GetMapping("/list")
    public List<DrillPlan> listDrillPlans() {
        return drillPlanService.list();
    }
}