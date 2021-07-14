package com.mdgd.j_commons.mvp;

import com.mdgd.commons.contract.activity.ActivityContract;
import com.mdgd.commons.contract.mvp.BasicPresenter;

/**
 * Created by Max
 * on 08/10/2017.
 */

public abstract class ActivityPresenter<T extends ActivityContract.View> extends BasicPresenter<T>
        implements ActivityContract.IPresenter<T> {
}
