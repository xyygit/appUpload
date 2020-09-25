package com.tilive.appupdate.controller;

import com.tilive.appupdate.bean.AppInfo;
import com.tilive.appupdate.config.ServerConfig;
import com.tilive.appupdate.service.AppService;
import com.tilive.appupdate.utils.ApkUtil;
import com.tilive.appupdate.utils.VersionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "应用上传管理")
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Value("${file.uploadFolder}")
    private String uploadFolder;//绝对路径
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;//相对路径

    @Autowired
    private AppService appService;

    @ApiOperation(value = "打开应用上传页面")
    @GetMapping("/upload")
    public String upload(Model model) {
        model.addAttribute("updateLog", "请填写应用版本更新日志");
        return "upload";
    }

    @ApiOperation(value = "上传应用")
    @PostMapping("/upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam("file") MultipartFile file,
                                      @RequestParam(value = "updateLog", required = false) String updateLog) {
        Map<String, String> resMap = new HashMap<>();
        if (file.isEmpty()) {
            resMap.put("error", "上传失败，请选择文件");
            return resMap;
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(uploadFolder + fileName);
        try {
            file.transferTo(dest);
            AppInfo appInfo = ApkUtil.readApk(dest);
            appInfo.setAppSize(file.getSize());
            appInfo.setUpdateContent(updateLog);
            appInfo.setDownloadUrl("http://localhost:8080/upload/" + fileName);
            appService.save(appInfo);
            LOGGER.info("上传成功" + serverConfig.getUrl() + "/" + uploadFolder + fileName);
            LOGGER.info("更新日志：" + updateLog);
            resMap.put("downloadUrl", "http://localhost:8080/upload/" + fileName);
            return resMap;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        resMap.put("error", "上传失败，请选择文件");
        return resMap;
    }

    @ApiOperation(value = "获取应用版本列表")
    @GetMapping("/getAppList")
    public String getAppList(Model model) {
        model.addAttribute("appList", appService.getAppInfoList());
        return "appList";
    }

    @ApiOperation(value = "获取最新版本")
    @RequestMapping("/updateApp/{version:.+}")
    @ResponseBody
    public Map<String, Object> getLastApp(@PathVariable String version) {
        Map<String, Object> lastAppMap = new HashMap<>();
        AppInfo appInfo = appService.getLastAppInfo();
        if (appInfo != null) {
            int compareVersion = VersionUtil.compareVersion(version, appInfo.getVersionName());
            lastAppMap.put("appVersion", appInfo.getVersionName());
            lastAppMap.put("versionCode", appInfo.getVersionCode());
            lastAppMap.put("updateTime", appInfo.getUpdateTime());
            if (compareVersion == -1) {//当前版本可更新
                lastAppMap.put("hasNewVersion", true);
                lastAppMap.put("appUpdateLog", appInfo.getUpdateContent());
                lastAppMap.put("downloadUrl", appInfo.getDownloadUrl());
                lastAppMap.put("apkSize", appInfo.getAppSize());
            } else {
                lastAppMap.put("hasNewVersion", false);
                lastAppMap.put("appUpdateLog", "已经是最新版本了");
            }
        } else {
            lastAppMap.put("hasNewVersion", false);
            lastAppMap.put("appUpdateLog", "还没上传新版应用");
        }
        return lastAppMap;
    }

}
