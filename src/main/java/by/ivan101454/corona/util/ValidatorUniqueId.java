package by.ivan101454.corona.util;

import java.util.HashSet;
import java.util.Set;

public class ValidatorUniqueId {

    private final Set<Integer> idSet = new HashSet<>();

    /**
     * Метод проверяет, есть ли уже такой id, если нет вносит в список
     * @param id проверемый идентификатор пользователя
     * @return true, если id уникален
     */
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
