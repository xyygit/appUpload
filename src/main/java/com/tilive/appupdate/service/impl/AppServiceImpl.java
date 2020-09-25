package com.tilive.appupdate.service.impl;

import com.tilive.appupdate.bean.AppInfo;
import com.tilive.appupdate.dao.AppVersionDao;
import com.tilive.appupdate.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppVersionDao appVersionDao;

    @Override
    public void save(AppInfo appInfo) {
        appVersionDao.save(appInfo);
    }

    @Override
    public List<AppInfo> getAppInfoList() {
        return appVersionDao.findAll();
    }

    @Override
    public AppInfo getLastAppInfo() {
        List<AppInfo> appInfoList = getAppInfoList();
        if (appInfoList != null && appInfoList.size() > 0) {
            return appInfoList.get(appInfoList.size() - 1);
        }
        return null;
    }
}
