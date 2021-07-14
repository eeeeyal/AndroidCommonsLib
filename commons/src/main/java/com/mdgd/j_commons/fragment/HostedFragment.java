package com.mdgd.j_commons.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mdgd.commons.contract.fragment.FragmentContract;
import com.mdgd.commons.contract.progress.ProgressDecor;
import com.mdgd.commons.utilities.PermissionsUtil;
import com.mdgd.j_commons.progress.ProgressDialogWrapper;

/**
 * Created by Max
 * on 19/07/2017.
 */

public abstract class HostedFragment<X extends FragmentContract.Presenter, Y extends FragmentContract.Host>
        extends Fragment implements FragmentContract.View {
    protected X presenter;

    private boolean hasProgress = false;
    private ProgressDecor progress;
    protected Y host;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
    }

    protected abstract X getPresenter();

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(Context context) {
        super.onAttach(context);
        host = getHostDelegate(context);
        if (host == null) host = (Y) context;
    }

    protected Y getHostDelegate(Context context) {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.subscribe(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final android.view.View v = inflater.inflate(getLayoutResId(), container, false);
        initViews(v);
        return v;
    }

    protected abstract int getLayoutResId();

    protected void initViews(final android.view.View v) {
    }

    @Override
    public boolean hasProgress() {
        return hasProgress;
    }

    public void setHasProgress(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }

    @Override
    public void showProgress() {
        showProgress("", "");
    }

    @Override
    public void showProgress(int titleRes, int messageRes) {
        showProgress(getString(titleRes), getString(messageRes));
    }

    @Override
    @CallSuper
    public void showProgress(String title, String message) {
        try {
            if (progress == null) progress = createProgressView(title, message);
            if (!progress.isShowing() && host != null && !host.isFinishing()) progress.show();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    protected ProgressDecor createProgressView(String title, String message) {
        return new ProgressDialogWrapper(getActivity(), title, message);
    }

    @Override
    @CallSuper
    public void hideProgress() {
        if (progress == null) return;
        progress.dismiss();
        progress = null;
    }

    @Override
    public void showToast(int msgRes) {
        final Context ctx = getActivity();
        if (ctx != null) Toast.makeText(ctx, msgRes, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(int msgRes, Object... query) {
        final Context ctx = getActivity();
        if (ctx != null) Toast.makeText(ctx, getString(msgRes, query), Toast.LENGTH_LONG).show();
    }


    @TargetApi(16)
    protected boolean requestPermissionsIfNeed(int requestCode, String... permissions) {
        return PermissionsUtil.requestPermissionsIfNeed(getActivity(), requestCode, permissions);
    }

    @TargetApi(16)
    protected boolean hasPermissions(String... permissions) {
        return PermissionsUtil.checkPermissions(getActivity(), permissions);
    }
}
