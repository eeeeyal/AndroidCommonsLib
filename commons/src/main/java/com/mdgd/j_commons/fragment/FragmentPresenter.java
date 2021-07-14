package com.mdgd.j_commons.fragment;

import com.mdgd.commons.contract.fragment.FragmentContract;
import com.mdgd.commons.contract.mvp.BasicPresenter;

/**
 * Created by Max
 * on 08/10/2017.
 */
public abstract class FragmentPresenter<T extends FragmentContract.View>  extends BasicPresenter<T>
        implements FragmentContract.Presenter<T> {
}
