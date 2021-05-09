package algs.nineth;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Tourists {
    public List<List<Integer>> way(Integer wayLong,
                                   Integer sunrise,
                                   Integer sundown,
                                   Integer velocity,
                                   List<Integer> pathFromStartToEachChill) {
        int currentTime = 0;
        int currentDay = 0;
        List<List<Integer>> daysWays = new ArrayList<>();
        Iterator<Integer> iterator = pathFromStartToEachChill.iterator();
        Integer wayDistance = iterator.next();
        while (iterator.hasNext()) {
            if (daysWays.size() <= currentDay) {
                daysWays.add(new ArrayList<>());
            }
            int totalDistance = daysWays
                    .stream().collect(
                    (Supplier<ArrayList<Integer>>) ArrayList::new,
                    ArrayList::addAll,
                    ArrayList::addAll)
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            if (totalDistance >= wayLong) {
                break;
            }
            int hoursInTheWay = getHoursByWayAndVelocity(velocity, wayDistance);
            if (currentTime + hoursInTheWay >= sundown - sunrise) {
                if (currentTime == 0) {
                    throw new InvalidParameterException("Длинна между пунктами  больше чем может пройти группа за день");
                }
                currentDay++;
                currentTime = 0;
            } else {
                currentTime += hoursInTheWay;
                daysWays.get(currentDay).add(wayDistance);
                wayDistance = iterator.next();
            }
        }
        return daysWays;
    }

    public static void main(String[] args) {
        System.out.println(new Tourists().way(100, 6, 22, 6, new ArrayList<>() {{
            add(10);
            add(15);
            add(35);
            add(50);
            add(25);
            add(30);
            add(80);
            add(5);
            add(45);
            add(60);
            add(100);
        }}));
    }

    private Integer getHoursByWayAndVelocity(Integer velocity, Integer wayDistance) {
        return wayDistance / velocity;
    }
}
