package com.singerstone.java.base;

/**
 * @author jarenzhang
 * 俊哥说别动他的代码
 */
public class Item {

    public String name;

    public int sellDeadline;

    public int price;

    public Item(String name, int sellDeadline, int price) {
        this.name = name;
        this.sellDeadline = sellDeadline;
        this.price = price;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellDeadline + ", " + this.price;
    }
}
