package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @des:
 * @author: yogachen
 * @date: 2023/9/17 10:44
 * @see {@link }
 */
public class 字母异位词分组 {

    public static void main(String[] args) {
        String[] array=new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new 字母异位词分组().groupAnagrams(array));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String key = String.valueOf(array);
            List<String> value = map.get(key);
            if (value == null) {
                value = new ArrayList<>();
            }
            value.add(s);
            map.put(key, value);
        }

        return new ArrayList<>(map.values());
    }
}
