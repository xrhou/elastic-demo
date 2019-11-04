package com.hxr.datetimedeeping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * 手写实现下一个工作日,过滤掉
 *
 * @author houxiurong
 * @date 2019-10-02
 */
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int day2Add = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            day2Add = 3;
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            day2Add = 2;
        }
        return temporal.plus(day2Add, ChronoUnit.DAYS);
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2019, 10, 4);

        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int day2Add = 1;
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                day2Add = 3;
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                day2Add = 2;
            }
            return temporal.plus(day2Add, ChronoUnit.DAYS);
        });
        LocalDate localDate = date.with(temporalAdjuster);
        System.out.println(localDate);
    }
}
