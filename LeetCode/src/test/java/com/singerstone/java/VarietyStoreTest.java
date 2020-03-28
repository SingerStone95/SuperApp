package com.singerstone.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class VarietyStoreTest {

    @Test
    public void updateItems() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        VarietyStore app = new VarietyStore(items);
        app.updateItems();
        assertEquals("fixme", app.items[0].name);
    }
}