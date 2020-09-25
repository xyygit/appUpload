package com.tilive.appupdate.utils;

/**
 * 版本工具
 */
public class VersionUtil {
    /**
     * 版本号比较
     *
     * @param v1
     * @param v2
     * @return 0代表相等，1代表v1大，-1代表v2大
     */
    public static int compareVersion(String v1, String v2) {
        Long versionCode1 = getVCode(v1);
        Long versionCode2 = getVCode(v2);
        return Long.compare(versionCode1, versionCode2);
    }

    public static Long getVCode(String versionName) {
        if (versionName == null || versionName.isEmpty()) return 0L;
        String[] numbers = versionName.split("\\.");
        Long versioncode = -1L;
        if (numbers == null || (numbers.length != 2 && numbers.length != 3 && numbers.length != 4)) {
            return 0L;
        }
        if (numbers.length == 4) {
            versioncode = Long.parseLong(numbers[0]) * 1000000 + Long.parseLong(numbers[1]) * 10000
                    + Long.parseLong(numbers[2]) * 100 + Long.parseLong(numbers[3]);
        }
        if (numbers.length == 3) {
            versioncode = Long.parseLong(numbers[0]) * 1000000 + Long.parseLong(numbers[1]) * 10000
                    + Long.parseLong(numbers[2]) * 1000;
        }
        if (numbers.length == 2) {
            versioncode = Long.parseLong(numbers[0]) * 1000000 + Long.parseLong(numbers[1]) * 10000;
        }
        if (versioncode == -1) {
            return 0L;
        }
        return versioncode;
    }
}