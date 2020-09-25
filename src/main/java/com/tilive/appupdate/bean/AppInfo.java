package com.tilive.appupdate.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "app")
@ApiModel(description = "安卓安装包版本信息")
public class AppInfo extends BaseBean {
    @ApiModelProperty("更新时间")
    private Long updateTime;
    @ApiModelProperty("更新日志")
    private String updateContent;
    @ApiModelProperty("APP版本")
    private String versionName;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("包名")
    private String pkgName;
    @ApiModelProperty("版本号")
    private Long versionCode;
    @ApiModelProperty("应用图标")
    private String icon;
    @ApiModelProperty("安装包大小")
    private Long appSize;
    @ApiModelProperty("APK下载链接")
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
