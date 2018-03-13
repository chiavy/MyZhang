package com.qlktest;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class UtilString {
    /**
     * 从最后一个字符开始获取，获取它之后的字符串，不包括该字符
     */
    public static String getStringFromLastIndex(String origin, String symbol) {
        if (isBlank(origin)) {
            return "";
        }
        try {
            if (origin.lastIndexOf(symbol) < 0) {
                return origin;
            }
            return origin.substring(origin.lastIndexOf(symbol) + symbol.length(), origin.length());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 去除字符串最后一个"," 以及该字符后面的
     */
    public static String getStringWithoutLast(String origin) {
        return getStringWithoutLast(origin, ",");
    }

    /**
     * 去除字符串最后一个字符，以及该字符后面的
     */
    public static String getStringWithoutLast(String origin, String symbol) {
        int position = origin.lastIndexOf(symbol);
        if (position > 0) {
            return origin.substring(0, position);
        } else if (position == 0) {
            return "";
        }
        return origin;
    }

    /**
     * http://www.baidu.com/file.jpg
     * 获取文件名，不包括后缀
     *
     * @param http_url
     * @return file
     */
    public static String getHttplastnameWithoutDotAndLine(String http_url) {

        try {
            int last_dot_position = http_url.lastIndexOf(".");

            if (last_dot_position > 0) {
                http_url = http_url.substring(0, last_dot_position);
            }

            int last_line_position = http_url.lastIndexOf("/");

            if (last_line_position > 0) {
                http_url = http_url.substring(last_line_position + 1, http_url.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return http_url;
    }

    /**
     * 给String高亮显示
     *
     * @param str
     * @param textview
     */
    public static void setLightString(String str, TextView textview, int start, int end, String color) {
        // 实体对象值显示在控件上
        SpannableString hightlight = new SpannableString(str);
        // 高亮器
        ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor(color));
        hightlight.setSpan(span, start, end, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        textview.setText(hightlight);
    }

    /**
     * 字符串是否是空白
     *
     * @param str
     * @return true 空白
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);// trim()也可以去除制表符
    }

    /**
     * Filter string.
     *
     * @param str
     * @return
     */
    public static String f(String str) {
        if (isBlank(str)) {
            return "";
        }
        return str.trim();
    }

    /**
     * 是否是号码
     *
     * @param num 电话号码
     * @return true为是
     */
    public static boolean isPhoneNum(String num) {
        if (num != null && num.length() == 11) {
            // if (num.matches("1[34568]\\d{9}")) {
            // 访问网络获取验证码
            return true;
            // }
        }
        return false;
    }

    /**
     * 字符串转int
     *
     * @param str      字符串
     * @param defValue 转换失败时的默认值
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 字符串转int
     *
     * @param obj 字符串
     * @return 如果转换失败为0
     */
    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        } else if (obj.toString().toString().replace(" ", "").equals("")) {
            return 0;
        }
        return toInt(obj.toString(), 0);
    }

    /**
     * 字符串转long
     *
     * @param obj 字符串
     * @return 如果转换失败为0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 字符串转double
     *
     * @param obj 字符串
     * @return 如果转换失败为0D
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0D;
    }

    /**
     * 字符串转boolean
     *
     * @param b 字符串
     * @return 如果转换失败为false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 字符串转boolean
     *
     * @param b             字符串
     * @param default_value 转化失败的默认值
     * @return
     */
    public static boolean toBool(String b, boolean default_value) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
            return default_value;
        }
    }

    /**
     * 是否是数字
     *
     * @param str 字符串
     * @return true为是
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 大于15位，则截取14位数的字符串
     *
     * @param imei
     * @return
     */
    public static String get14IMEI(String imei) {

        if (!isBlank(imei)) {
            if (imei.length() >= 15) {
                return imei.substring(0, 14);
            } else {
                //小于15的情况
                return imei;
            }
        } else {
            return null;
        }

    }

    /**
     * 截取字符串 （一个字符当作一个长度单位，不区分数字和汉字的不同字节占位）
     * @param src 原字符串
     * @param wantLen 想要限制的长度
     * @param suffix 如果超过限制的长度 拼接自定义后缀
     * @return
     */
    public static String cutString(String src,int wantLen,String suffix){
        if(src == null){
            return "";
        }
        if (suffix == null) {
            suffix = "";
        }
        int srcLen = src.length();
        //不需截取
        if(src.length() <= wantLen){
            return src;
        }
        //需要截取
        return src.substring(0,wantLen) + suffix;
    }

    /**
     * 是否传入的所有字符串都不为空
     * @param strs
     * @return
     */
    public static boolean isAllNotBlank(String... strs){
        for(String str : strs ){
            if(UtilString.isBlank(str)){
                return false;
            }
        }
        return true;
    }

}
