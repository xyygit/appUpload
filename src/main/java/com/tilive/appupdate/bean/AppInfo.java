package com.tilive.appupdate.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "app")
public class AppInfo extends BaseBean {
    private Long updateTime;
    private String updateContent;
    private String versionName;
    private String fileName;
    private String pkgName;
    private Long versionCode;
    private String icon;
    private Long appSize;
    private String downloadUrl;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getAppSize() {
        return appSize;
    }

    public void setAppSize(Long appSize) {
        this.appSize = appSize;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "updateTime=" + updateTime +
                ", updateContent='" + updateContent + '\'' +
                ", versionName='" + versionName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", pkgName='" + pkgName + '\'' +
                ", versionCode=" + versionCode +
                ", icon='" + icon + '\'' +
                ", appSize=" + appSize +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", id=" + id +
                '}';
    }
}
