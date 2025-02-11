package by.ivan101454.corona.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ValidatorUniqueId {

    private final Set<Integer> idSet = new HashSet<>();

    public boolean checkUnique(int id) {
        boolean contains = idSet.contains(id);
        if(contains) {
            return false;
        } else {
            idSet.add(id);
            return true;
        }
    }

}
