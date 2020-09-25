package com.tilive.appupdate.service;

import com.tilive.appupdate.bean.AppInfo;

import java.util.List;

public interface AppService {
    void save(AppInfo appInfo);

    List<AppInfo> getAppInfoList();

    AppInfo getLastAppInfo();
}
