package com.velesmarket.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static List<List<Object>> createNestedList(List<Object> originalList, int sublistSize) {
        List<List<Object>> nestedList = new ArrayList<>();
        for (int i = 0; i < originalList.size(); i += sublistSize) {
            int endIndex = Math.min(i + sublistSize, originalList.size());
            List<Object> sublist = originalList.subList(i, endIndex);
            nestedList.add(sublist);
        }
        return nestedList;
    }
}
