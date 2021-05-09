package algs.nineth;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZavodPokupaet {
    public List<Object> toBuy(List<Integer> growthIndex) {
        List<Object> res = Arrays.asList(Stream.of(growthIndex
                .stream()
                .collect(Collectors.toMap(key -> key, growthIndex::indexOf)))
                .map(Map::values)
                .findFirst()
                .get()
                .toArray());
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ZavodPokupaet().toBuy(new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(6);
            add(14);
            add(15);
            add(4);
        }}));
    }
}