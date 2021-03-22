package com.samutech.dailyluck.fragment;

import com.samutech.dailyluck.model.WinnersModel;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

class SortByDrawNo implements Comparator<WinnersModel> {
    @Override
    public int compare(WinnersModel winnersModel, WinnersModel t1) {
        String a=winnersModel.getDraw();
        String b=t1.getDraw();
        a = a.replaceAll("[^\\d.]", "");
        b = b.replaceAll("[^\\d.]", "");

        return Integer.valueOf(a)-Integer.valueOf(b);
    }

    @Override
    public Comparator<WinnersModel> reversed() {
        return null;
    }

    @Override
    public Comparator<WinnersModel> thenComparing(Comparator<? super WinnersModel> other) {
        return null;
    }

    @Override
    public <U> Comparator<WinnersModel> thenComparing(Function<? super WinnersModel, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<WinnersModel> thenComparing(Function<? super WinnersModel, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<WinnersModel> thenComparingInt(ToIntFunction<? super WinnersModel> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<WinnersModel> thenComparingLong(ToLongFunction<? super WinnersModel> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<WinnersModel> thenComparingDouble(ToDoubleFunction<? super WinnersModel> keyExtractor) {
        return null;
    }
}
