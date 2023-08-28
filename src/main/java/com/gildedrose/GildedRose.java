package com.gildedrose;

import static com.gildedrose.Constant.*;
import static java.lang.Math.negateExact;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!LEGENDARY_ITEM_NAMES.contains(item.name)) {
                item.sellIn--;

                final boolean sellInPassed = item.sellIn < 0;
                int addQuality = sellInPassed ? 2 : 1;

                if (EXPIRE_ITEM_NAMES.contains(item.name)) {
                    addQuality = sellInPassed ? negateExact(item.quality) : Math.max(3 - (item.sellIn / 5), 1);
                } else if (!INCREASE_QUALITY_ITEM_NAMES.contains(item.name)) {
                    if (item.name.contains(CONJURED)) {
                        addQuality = addQuality * 2;
                    }
                    addQuality = negateExact(addQuality);
                }

                calculateQuality(item, addQuality);
            }
        }
    }

    /**
     * Add "i" to an item quality
     * Keep the quality between MIN_QUALITY & MAX_QUALITY
     * @param item : item from which quality will change
     * @param i : int value to be added
     */
    private static void calculateQuality(Item item, int i) {
        item.quality = Math.max(Math.min((item.quality + i), Constant.MAX_QUALITY), Constant.MIN_QUALITY);
    }
}
