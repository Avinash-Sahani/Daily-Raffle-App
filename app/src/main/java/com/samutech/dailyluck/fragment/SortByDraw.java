package com.samutech.dailyluck.fragment;

import com.samutech.dailyluck.model.ResultModel;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

class SortByDraw implements Comparator<ResultModel> {
    @Override
    public int compare(ResultModel resultModel, ResultModel t1) {

       String a=resultModel.getName();
       String b=t1.getName();
        a = a.replaceAll("[^\\d.]", "");
        b = b.replaceAll("[^\\d.]", "");

        return Integer.valueOf(a)-Integer.valueOf(b);
    }

    @Override
    public Comparator<ResultModel> reversed() {
        return null;
    }

    @Override
    public Comparator<ResultModel> thenComparing(Comparator<? super ResultModel> other) {
        return null;
    }

    @Override
    public <U> Comparator<ResultModel> thenComparing(Function<? super ResultModel, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<ResultModel> thenComparing(Function<? super ResultModel, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<ResultModel> thenComparingInt(ToIntFunction<? super ResultModel> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<ResultModel> thenComparingLong(ToLongFunction<? super ResultModel> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<ResultModel> thenComparingDouble(ToDoubleFunction<? super ResultModel> keyExtractor) {
        return null;
    }
}
