package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.Constant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQuality() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item("any", 1, 1),
            new Item("any", 0, 2)
        });
        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

        assertEquals(-1, app.items[1].sellIn);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    void updateQuality_min_quality() {
        final GildedRose app = new GildedRose(new Item[] { new Item("any", 0, MIN_QUALITY) });
        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(MIN_QUALITY, app.items[0].quality);
    }

    @Test
    void updateQuality_legendary_above_max_quality() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item(Constant.LEGENDARY_ITEM_NAMES.get(0), 1, MAX_QUALITY+1)
        });
        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
        assertEquals(MAX_QUALITY+1, app.items[0].quality);
    }

    @Test
    void updateQuality_increase_quality() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item(Constant.INCREASE_QUALITY_ITEM_NAMES.get(0), 1, 1),
            new Item(Constant.INCREASE_QUALITY_ITEM_NAMES.get(0), 0, 1)
        });
        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);

        assertEquals(-1, app.items[1].sellIn);
        assertEquals(3, app.items[1].quality);
    }

    @Test
    void updateQuality_increase_quality_max() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item(Constant.INCREASE_QUALITY_ITEM_NAMES.get(0), 1, MAX_QUALITY)
        });
        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(MAX_QUALITY, app.items[0].quality);
    }

    @Test
    void updateQuality_expire_item() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item(Constant.EXPIRE_ITEM_NAMES.get(0), 11, 10),
            new Item(Constant.EXPIRE_ITEM_NAMES.get(0), 10, 10),
            new Item(Constant.EXPIRE_ITEM_NAMES.get(0), 5, 10)
        });
        app.updateQuality();

        assertEquals(10, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);

        assertEquals(9, app.items[1].sellIn);
        assertEquals(12, app.items[1].quality);

        assertEquals(4, app.items[2].sellIn);
        assertEquals(13, app.items[2].quality);
    }

    @Test
    void updateQuality_expire_item_passed() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item(Constant.EXPIRE_ITEM_NAMES.get(0), 0, 10)
        });
        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void updateQuality_conjured() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item("any " + CONJURED, 1, 10),
            new Item(CONJURED + " any", 0, 10)
        });
        app.updateQuality();

        assertEquals(0, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);

        assertEquals(-1, app.items[1].sellIn);
        assertEquals(6, app.items[1].quality);
    }

    @Test
    void updateQuality_conjured_are_not_an_exception() {
        final GildedRose app = new GildedRose(new Item[] {
            new Item(CONJURED + " " + LEGENDARY_ITEM_NAMES.get(0), 1, 10),
            new Item(CONJURED + " " + INCREASE_QUALITY_ITEM_NAMES.get(0), 1, 10),
            new Item(CONJURED + " " + EXPIRE_ITEM_NAMES.get(0), 1, 10)
        });
        app.updateQuality();

        assertEquals(8, app.items[0].quality);
        assertEquals(8, app.items[1].quality);
        assertEquals(8, app.items[2].quality);
    }
}
