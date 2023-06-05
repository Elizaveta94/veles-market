package com.velesmarket.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
        public static List<List<String>> createNestedList(List<String> originalList, int sublistSize) {
        List<List<String>> nestedList = new ArrayList<>();
        for (int i = 0; i < originalList.size(); i += sublistSize) {
            int endIndex = Math.min(i + sublistSize, originalList.size());
            List<String> sublist = originalList.subList(i, endIndex);
            nestedList.add(sublist);
        }
        return nestedList;
    }
}
