package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日付フォーマットのUtil
 */
public class DateFormatter {

    private static final String YYYYMMDD = "yyyyMMdd";
    private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * Date型から"yyyyMMdd"形式のStringに変換します 使用例 : String strDate =
     * DateFormatter.yyyyMMdd(new Date());
     *
     * @param date 基準日
     * @return yyyyMMdd形式の文字列
     */
    public static String yyyyMMdd(Date date) {
        return new SimpleDateFormat(YYYYMMDD).format(date);
    }

    /**
     * "yyyyMMdd"形式のStringからDate型に変換します 使用例 : Date date =
     * DateFormatter.yyyyMMddToDate("20210901");
     *
     * @param yyyyMMdd 基準日
     * @return Date
     */
    public static Date yyyyMMddToDate(String yyyyMMdd) {
        try {
            return new SimpleDateFormat(YYYYMMDD).parse(yyyyMMdd);
        } catch (ParseException e) {
            throw new RuntimeException("Formatting failed. Please pass an argument in yyyymmdd format.");
        }
    }

    /**
     * Date型から"yyyyMMddHHmmss"形式のStringに変換します 使用例 : String strDate =
     * DateFormatter.yyyyMMddHHmmss(new Date());
     *
     * @param date 基準日
     * @return yyyyMMddHHmmss形式の文字列
     */
    public static String yyyyMMddhhmmss(Date date) {
        return new SimpleDateFormat(YYYYMMDDHHMMSS).format(date);
    }

    /**
     * "yyyyMMddHHmmss"形式のStringからDate型に変換します 使用例 : Date date =
     * DateFormatter.yyyyMMddHHmmssToDate("20210901123456");
     *
     * @param yyyyMMddhhmmss 基準日
     * @return Date
     */
    public static Date yyyyMMddhhmmssToDate(String yyyyMMddhhmmss) {
        try {
            return new SimpleDateFormat(YYYYMMDDHHMMSS).parse(yyyyMMddhhmmss);
        } catch (ParseException e) {
            throw new RuntimeException("Formatting failed. Please pass an argument in yyyyMMddHHmmss format.");
        }
    }

}
