package com.mdgd.j_commons.progress;

import androidx.fragment.app.FragmentActivity;

import com.mdgd.commons.contract.progress.ProgressDecor;

/**
 * Created by Max
 * on 05/09/2018.
 */
public class ProgressDialogWrapper implements ProgressDecor {

    private final ProgressDialogFragment progressView;
    private final FragmentActivity context;

    public ProgressDialogWrapper(FragmentActivity context, String title, String message) {
        progressView = ProgressDialogFragment.newInstance(title, message);
        this.context = context;
    }

    public ProgressDialogWrapper(FragmentActivity context, int title, int message) {
        progressView = ProgressDialogFragment.newInstance(title, message);
        this.context = context;
    }

    @Override
    public void show() {
        progressView.show(context.getSupportFragmentManager(), "progress");
    }

    @Override
    public boolean isShowing() {
        return progressView.isShowing();
    }

    @Override
    public void dismiss() {
        progressView.dismiss();
    }
}
