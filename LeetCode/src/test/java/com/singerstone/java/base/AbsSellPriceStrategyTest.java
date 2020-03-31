package com.singerstone.java.base;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.*;

/**
 * author : yogachen
 * date   : 2020-03-31
 * desc   :
 */
public class AbsSellPriceStrategyTest {

    @Test
    public void update_price_1() throws Exception {
        AbsSellPriceStrategy absSellPriceStrategy = PowerMockito.mock(AbsSellPriceStrategy.class);
        Item item = new Item("test", 2, 10);
        PowerMockito.doCallRealMethod().when(absSellPriceStrategy, "update", item);
        PowerMockito.doReturn(1).when(absSellPriceStrategy, "getPriceChangeCount", Mockito.anyInt());
        PowerMockito.doReturn(false).when(absSellPriceStrategy, "needLimitPrice");
        PowerMockito.doReturn(-1).when(absSellPriceStrategy, "getChangeSellDeadline");
        absSellPriceStrategy.update(item);
        Assert.assertEquals(11, item.price);
    }

    @Test
    public void update_price_2() throws Exception {
        AbsSellPriceStrategy absSellPriceStrategy = PowerMockito.mock(AbsSellPriceStrategy.class);
        Item item = new Item("test", 2, 10);
        PowerMockito.doCallRealMethod().when(absSellPriceStrategy, "update", item);
        PowerMockito.doReturn(2).when(absSellPriceStrategy, "getPriceChangeCount", Mockito.anyInt());
        PowerMockito.doReturn(false).when(absSellPriceStrategy, "needLimitPrice");
        PowerMockito.doReturn(-1).when(absSellPriceStrategy, "getChangeSellDeadline");
        absSellPriceStrategy.update(item);
        Assert.assertEquals(12, item.price);
    }

    @Test
    public void update_price_n1() throws Exception {
        AbsSellPriceStrategy absSellPriceStrategy = PowerMockito.mock(AbsSellPriceStrategy.class);
        Item item = new Item("test", 2, 10);
        PowerMockito.doCallRealMethod().when(absSellPriceStrategy, "update", item);
        PowerMockito.doReturn(-1).when(absSellPriceStrategy, "getPriceChangeCount", Mockito.anyInt());
        PowerMockito.doReturn(false).when(absSellPriceStrategy, "needLimitPrice");
        PowerMockito.doReturn(-1).when(absSellPriceStrategy, "getChangeSellDeadline");
        absSellPriceStrategy.update(item);
        Assert.assertEquals(9, item.price);
    }

    @Test
    public void update_price_limit_true() throws Exception {
        AbsSellPriceStrategy absSellPriceStrategy = PowerMockito.mock(AbsSellPriceStrategy.class);
        Item item = new Item("test", 2, 50);
        PowerMockito.doCallRealMethod().when(absSellPriceStrategy, "update", item);
        PowerMockito.doReturn(1).when(absSellPriceStrategy, "getPriceChangeCount", Mockito.anyInt());
        PowerMockito.doReturn(true).when(absSellPriceStrategy, "needLimitPrice");
        PowerMockito.doReturn(-1).when(absSellPriceStrategy, "getChangeSellDeadline");
        PowerMockito.doReturn(50).when(absSellPriceStrategy, "getMaxPrice");
        absSellPriceStrategy.update(item);
        Assert.assertEquals(50, item.price);
    }
    @Test
    public void update_price_limit_min_true() throws Exception {
        AbsSellPriceStrategy absSellPriceStrategy = PowerMockito.mock(AbsSellPriceStrategy.class);
        Item item = new Item("test", 2, 0);
        PowerMockito.doCallRealMethod().when(absSellPriceStrategy, "update", item);
        PowerMockito.doReturn(-1).when(absSellPriceStrategy, "getPriceChangeCount", Mockito.anyInt());
        PowerMockito.doReturn(true).when(absSellPriceStrategy, "needLimitPrice");
        PowerMockito.doReturn(-1).when(absSellPriceStrategy, "getChangeSellDeadline");
        PowerMockito.doReturn(0).when(absSellPriceStrategy, "getMinPrice");
        absSellPriceStrategy.update(item);
        Assert.assertEquals(0, item.price);
    }
}