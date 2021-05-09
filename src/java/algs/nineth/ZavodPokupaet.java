package algs.nineth;

import java.util.*;
import java.util.stream.Collectors;

public class ZavodPokupaet {
    public List<Object> toBuy(List<Integer> growthIndex) {
        return Collections.singletonList(Optional.of(growthIndex
                .stream()
                .collect(Collectors.toMap(key -> key, growthIndex::indexOf)))
                .get()
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                .values());
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