package com.gildedrose;

import java.util.Collections;
import java.util.List;

//Use constants in order to facilitate externalization (DB or properties)
public class Constant {

    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    //Lists used to add new exceptions in the future in needed
    public static final List<String> LEGENDARY_ITEM_NAMES = Collections.singletonList(SULFURAS);
    public static final List<String> INCREASE_QUALITY_ITEM_NAMES = Collections.singletonList(AGED_BRIE);
    public static final List<String> EXPIRE_ITEM_NAMES = Collections.singletonList(BACKSTAGE);

    public static final String CONJURED = "Conjured";
}
