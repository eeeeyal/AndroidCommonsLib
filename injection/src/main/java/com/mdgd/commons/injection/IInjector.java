package com.mdgd.commons.injection;

import androidx.annotation.NonNull;

/**
 * Created by Owner
 * on 13/05/2019.
 */
public interface IInjector<P, V> {

    @NonNull
    P createPresenter(V view);
}