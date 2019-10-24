package com.example.timechecker;

import java.util.HashMap;
import java.util.Map;

public class Const {
    public static String ENTITY_1_NAME = "Shina96Otsu-ShinagawaEki";
    static String ENTITY_1_URL = "https://tobus.jp/blsys/navi?VCD=cslrst&ECD=NEXT&LCD=&func=ftt&method=mrw&sldrw=1003_4025_7314&slst=&slrsp=165_605_5_1_1";

    static Map<String, String> nameUrlMap = new HashMap<>();

    static {
        nameUrlMap.put(ENTITY_1_NAME, ENTITY_1_URL);
    }

    public static String getUrl(String entityName) {
        if (nameUrlMap.containsKey(entityName))
            return nameUrlMap.get(entityName);
        throw new IllegalArgumentException("ENTITY NAME NOT FOUND!");
    }
}
