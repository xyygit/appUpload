package com.tilive.appupdate.controller;

import com.tilive.appupdate.bean.AppInfo;
import com.tilive.appupdate.config.ServerConfig;
import com.tilive.appupdate.service.AppService;
import com.tilive.appupdate.utils.ApkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/upload")
    public String upload(Model model) {
        model.addAttribute("updateLog", "填写更新日志");
        return "upload";
    }

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

    @GetMapping("/getAppList")
    public String getAppList(Model model) {
        model.addAttribute("appList", appService.getAppInfoList());
        return "appList";
    }

}
