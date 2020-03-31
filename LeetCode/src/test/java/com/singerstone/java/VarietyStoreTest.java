package com.singerstone.java;

import com.singerstone.java.base.Item;
import com.singerstone.java.base.ItemSellWrapper;
import com.singerstone.java.impl.BananaPriceStrategy;
import com.singerstone.java.impl.DiamondPriceStrategy;
import com.singerstone.java.impl.MaoTaiPriceStrategy;
import com.singerstone.java.impl.NormalPriceStrategy;
import com.singerstone.java.impl.QiyiPriceStrategy;

import org.junit.Test;

import static org.junit.Assert.*;

public class VarietyStoreTest {

    @Test
    public void updateNormalItems1() {
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("面包", 10, 20), new NormalPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        app.updateItems();
        assertEquals(19, app.items[0].getPrice());
    }


    @Test
    public void updateNormalItems19() {
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("面包", 10, 20), new NormalPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        for (int i = 0; i < 19; i++) {
            app.updateItems();
        }
        assertEquals(0, app.items[0].getPrice());
    }

    @Test
    public void updateNormalItems5() {
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("面包", 10, 20), new NormalPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        for (int i = 0; i < 5; i++) {
            app.updateItems();
        }
        assertEquals(15, app.items[0].getPrice());
    }

    @Test
    public void updateMaoTaiItems5() {
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("茅台", 10, 20), new MaoTaiPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        for (int i = 0; i < 5; i++) {
            app.updateItems();
        }
        assertEquals(25, app.items[0].getPrice());
    }


    @Test
    public void updatDiamondItems4() {
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("钻石", 0, 80), new DiamondPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        for (int i = 0; i < 4; i++) {
            app.updateItems();
        }
        assertEquals(80, app.items[0].getPrice());
    }


    @Test
    public void updaBananaItems4() {
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("香蕉", 3, 6), new BananaPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        for (int i = 0; i < 4; i++) {
            app.updateItems();
        }
        assertEquals(0, app.items[0].getPrice());
    }


    @Test
    public void updaBananaItems2(){
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("香蕉", 3, 6), new BananaPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        for (int i = 0; i < 2; i++) {
            app.updateItems();
        }
        assertEquals(2, app.items[0].getPrice());
    }

    @Test
    public void updaQiyiItems3(){
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("奇异果", 15, 20), new QiyiPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        for (int i = 0; i < 3; i++) {
            app.updateItems();
        }
        assertEquals(23, app.items[0].getPrice());
    }

    @Test
    public void updaQiyiItems2(){
        ItemSellWrapper[] items = new ItemSellWrapper[]{
                new ItemSellWrapper(new Item("奇异果", 1, 20), new QiyiPriceStrategy())
        };
        BateVarietyStore app = new BateVarietyStore(items);
        VarietyStore appold=new VarietyStore(new Item[]{
                new Item("奇异果", 1, 20)
        });
        for (int i = 0; i < 2; i++) {
            app.updateItems();
            appold.updateItems();
        }
        assertEquals(appold.items[0].price, app.items[0].getPrice());
    }
}