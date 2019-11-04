package com.hxr.datetimedeeping;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * java 8 LocalDate LocalTime总结学习
 *
 * @author houxiurong
 * @date 2019-10-02
 */
public class LocalDateTimeDemo {

    public static void main0(String[] args) {
        LocalDate date = LocalDate.of(2019, 10, 2);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek week = date.getDayOfWeek();
        boolean leapYear = date.isLeapYear();
        System.out.println("year:" + year);
        System.out.println("month:" + month);
        System.out.println("day:" + day);
        System.out.println("week:" + week);
        System.out.println("leapYear:" + leapYear);
        System.out.println("now:" + LocalDate.now());
        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));
    }

    public static void main2(String[] args) {
        LocalDate date = LocalDate.parse("2019-10-02");
        LocalTime time = LocalTime.parse("10:26:22");

        LocalDateTime localDateTime = date.atTime(time);
        System.out.println(localDateTime);
        System.out.println(localDateTime.toLocalDate());

        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(4, -1_000_000_000);
        System.out.println(instant1);
        System.out.println(instant2);
        /*int i = Instant.now().get(ChronoField.DAY_OF_MONTH);
        System.out.println(i);*/
        Duration duration = Duration.ofDays(1);
        System.out.println(duration);

        Period between = Period.between(LocalDate.of(2019, 10, 02),
                LocalDate.of(2019, 10, 12));
        System.out.println(between);

        LocalDate date1 = LocalDate.of(2019, 10, 2);
        LocalDate date2 = date1.withYear(2019);
        System.out.println("date2=" + date2);//2019-10-02
        LocalDate date3 = date2.withDayOfMonth(25);
        System.out.println("date3=" + date3);//2019-10-25
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 8);
        System.out.println("date4=" + date4);//2019-09-25
    }

    public static void main3(String[] args) {
        LocalDate date = LocalDate.of(2019, 10, 2);
        LocalDate date1 = date.plusWeeks(1);//2019-10-09
        System.out.println(date1);
        LocalDate date2 = date1.minusYears(3);//2016-10-09
        System.out.println(date2);
        LocalDate date3 = date1.plus(6, ChronoUnit.MONTHS);//2020-04-09
        System.out.println(date3);
    }

    public static void main4(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        date.withYear(2011);//新的日期未赋值给 date 因而输出 2011-09-08
        System.out.println(date);//2016-09-08
    }


    public static void main5(String[] args) {
        LocalDate date = LocalDate.of(2019, 10, 2);
        LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date1);//2019-10-06
        LocalDate date2 = date1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date2);
        LocalDate date3 = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("date3=" + date3);

        //表示第3周的星期五是哪一天
        LocalDate date4 = date.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY));
        System.out.println("date4=" + date4);//date4=2019-10-18
    }

    public static void main6(String[] args) {
        LocalDate date0 = LocalDate.parse("20191002", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date1 = LocalDate.parse("2019-10-02", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date0);
        System.out.println(date1);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.of(2019, 10, 2);
        String format = localDate.format(dateTimeFormatter);
        System.out.println("format=" + format);
        LocalDate parse = LocalDate.parse(format, dateTimeFormatter);
        System.out.println("parse=" + parse);


        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date = LocalDate.of(2019, 10, 2);
        String formatDate = date.format(italianFormatter);
        System.out.println("formatDate=" + formatDate);
        LocalDate date2 = LocalDate.parse(formatDate, italianFormatter);
        System.out.println("date2=" + date2);
    }

    //不同时区处理
    public static void main7(String[] args) {
        ZoneId zoneId = ZoneId.of("Europe/Rome");
        LocalDate date = LocalDate.of(2019, Month.OCTOBER, 2);
        ZonedDateTime zonedDateTime = date.atStartOfDay(zoneId);
        System.out.println(zonedDateTime);//2019-10-02T00:00+02:00[Europe/Rome]

        LocalDateTime dateTime = LocalDateTime.of(2019, 10, 2, 13, 45);
        ZonedDateTime dateTime1 = dateTime.atZone(zoneId);
        System.out.println(dateTime1);//2019-10-02T13:45+02:00[Europe/Rome]

        JapaneseDate japaneseDate = JapaneseDate.from(date);
        System.out.println(japaneseDate);//Japanese Heisei 31-10-02 平成 Japanese Showa 5-10-02
    }

    //计算伊斯兰教历-中斋月的起始和终止
    public static void main8(String[] args) {
        //取得当前伊斯兰教日期,之后对其进行修正,得到斋月的第:天-第9个月
        HijrahDate hijrahDate = HijrahDate.now()
                .with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MONTH_OF_YEAR, 9);
        //now-斋月开始:2020-04-24,结束日期在:2020-05-23
        System.out.println("斋月开始:" + IsoChronology.INSTANCE.date(hijrahDate) + ",结束日期在:" +
                IsoChronology.INSTANCE.date(hijrahDate.with(TemporalAdjusters.lastDayOfMonth())));
    }


}
