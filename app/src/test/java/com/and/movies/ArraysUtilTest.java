package com.and.movies;

import com.and.movies.list.ArraysUtil;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class ArraysUtilTest {

    @Test
    public void lowerBound_emptyArray() {
        final int lowerBound = ArraysUtil.lowerBound(new int[]{}, 0);
        Assert.assertThat(lowerBound, Is.is(0));
    }

    @Test
    public void lowerBound_singleItem_smallerKey() {
        final int lowerBound = ArraysUtil.lowerBound(new int[]{10}, 5);
        Assert.assertThat(lowerBound, Is.is(0));
    }

    @Test
    public void lowerBound_singleItem_biggerKey() {
        final int lowerBound = ArraysUtil.lowerBound(new int[]{10}, 15);
        Assert.assertThat(lowerBound, Is.is(1));
    }

    @Test
    public void lowerBound_twoItems_middleKey() {
        final int lowerBound = ArraysUtil.lowerBound(new int[]{10, 20}, 15);
        Assert.assertThat(lowerBound, Is.is(1));
    }

    @Test
    public void lowerBound_testArray_equalKey() {
        final int lowerBound = ArraysUtil.lowerBound(new int[]{92, 154, 185, 342, 500, 780}, 154);
        Assert.assertThat(lowerBound, Is.is(1));
    }

    @Test
    public void lowerBound_testArray_notEqualKey() {
        final int lowerBound = ArraysUtil.lowerBound(new int[]{92, 154, 185, 342, 500, 780}, 386);
        Assert.assertThat(lowerBound, Is.is(4));
    }
}
