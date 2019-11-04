package com.hxr.lambdastream;

import com.alibaba.fastjson.JSON;
import com.hxr.lambdastream.forkstream.Results;
import com.hxr.lambdastream.forkstream.StreamForker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的fork处理
 *
 * @author houxiurong
 * @date 2019-10-02
 */
public class ForkStreamDemo {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        Stream<Dish> stream = menu.stream();
        Results results = new StreamForker<>(stream)
                .fork("shortMenu", s -> s.map(Dish::getName).collect(Collectors.joining(", ")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloriesDish", s -> s.collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get())
                .fork("dishesByType", s -> s.collect(Collectors.groupingBy(Dish::getType)))
                .getResults();
        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloriesDish = results.get("mostCaloriesDish");
        Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println("Short Menu: " + shortMenu);
        System.out.println("totalCalories: " + totalCalories);
        System.out.println("mostCaloriesDish: " + JSON.toJSONString(mostCaloriesDish));
        System.out.println("dishesByType: " + JSON.toJSONString(dishesByType));
    }
}
