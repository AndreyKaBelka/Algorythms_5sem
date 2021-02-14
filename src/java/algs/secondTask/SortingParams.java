package algs.secondTask;

import java.util.Comparator;

public class SortingParams {
    private boolean byKey = false;
    private boolean asc = true;
    private Comparator<Integer> comparator = (o1, o2) -> 0;

    public SortingParams sortByKey() {
        byKey = true;
        return this;
    }

    public SortingParams ascSorting() {
        asc = true;
        return this;
    }

    public SortingParams sortByValue() {
        byKey = false;
        return this;
    }

    public SortingParams descSorting() {
        asc = false;
        return this;
    }

    Comparator<Integer> getComparator() {
        if (asc) {
            comparator = Comparator.reverseOrder();
        } else {
            comparator = Integer::compareTo;
        }
        return comparator;
    }

    public boolean isByKey() {
        return byKey;
    }
}
