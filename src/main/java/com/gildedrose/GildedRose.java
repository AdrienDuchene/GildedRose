package com.gildedrose;

import java.util.Arrays;

import static com.gildedrose.Constant.*;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
            .filter(item -> !LEGENDARY_ITEM_NAMES.contains(item.name))
            .forEach(GildedRose::updateItemQuality);
    }

    /**
     * Update quality and sellIn of an item
     * @param item : item to be updated
     */
    private static void updateItemQuality(Item item) {
        item.sellIn--;

        final boolean sellInPassed = item.sellIn < 0;
        int addQuality = sellInPassed ? 2 : 1;

        if (EXPIRE_ITEM_NAMES.contains(item.name)) {
            //Expire items will expire (quality=0) if sellIn is passed else the quality increase depending on sellIn
            addQuality = sellInPassed ? -item.quality : Math.max(3 - (item.sellIn / 5), 1);
        } else if (!INCREASE_QUALITY_ITEM_NAMES.contains(item.name)) {
            //Conjured items double the quality change
            if (item.name.contains(CONJURED)) {
                addQuality *= 2;
            }
            //if not in the increase list then quality will decrease
            addQuality = -addQuality;
        }

        calculateQuality(item, addQuality);
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
