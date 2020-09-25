package com.tilive.appupdate.utils;

import com.tilive.appupdate.bean.AppInfo;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.UseFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ApkUtil {
    private static final Logger logger = LoggerFactory.getLogger(ApkUtil.class);

    public static AppInfo readApk(File apkUrl) {
        AppInfo appInfo = new AppInfo();
        try (ApkFile apkFile = new ApkFile(apkUrl)) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            appInfo.setFileName(apkMeta.getName());
            appInfo.setIcon(apkMeta.getIcon());
            appInfo.setPkgName(apkMeta.getPackageName());
            appInfo.setVersionCode(apkMeta.getVersionCode());
            appInfo.setVersionName(apkMeta.getVersionName());
            appInfo.setUpdateTime(System.currentTimeMillis());

            for (UseFeature feature : apkMeta.getUsesFeatures()) {
                System.out.println(feature.getName());
            }
            logger.info("上传apk信息：" + apkMeta.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appInfo;
    }

}
