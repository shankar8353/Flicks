package com.codepath.flicks;

/**
 * Created by ssunda1 on 5/21/16.
 */
public interface Consumer<T> {

    void accept(T t);
}
