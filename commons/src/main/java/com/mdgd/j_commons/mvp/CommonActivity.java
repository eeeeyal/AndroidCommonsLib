package com.mdgd.j_commons.mvp;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mdgd.commons.contract.activity.ActivityContract;
import com.mdgd.commons.contract.progress.ProgressDecor;
import com.mdgd.commons.utilities.PermissionsUtil;
import com.mdgd.j_commons.R;
import com.mdgd.j_commons.progress.ProgressDialogWrapper;

/**
 * Created by Max
 * on 01/01/2018.
 */
public abstract class CommonActivity<T extends ActivityContract.IPresenter> extends AppCompatActivity
        implements ActivityContract.View {

    private boolean hasProgress = true;
    protected T presenter;
    private ProgressDecor progress;

    protected abstract T getPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        presenter = getPresenter();
        presenter.subscribe(this);
        getLifecycle().addObserver(presenter);
    }

    protected abstract int getLayoutResId();

    protected int getFragmentContainerId() {
        return R.id.fragmentContainer;
    }

    @Override
    protected void onStop() {
        hideProgress();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribe();
        getLifecycle().removeObserver(presenter);
        super.onDestroy();
    }

    @TargetApi(16)
    protected boolean requestPermissionsIfNeed(int requestCode, String... permissions) {
        return PermissionsUtil.requestPermissionsIfNeed(this, requestCode, permissions);
    }

    @TargetApi(16)
    protected boolean hasPermissions(String... permissions) {
        return PermissionsUtil.checkPermissions(this, permissions);
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
    public void showProgress(String title, String message) {
        try {
            if (progress == null) progress = createProgressView(title, message);
            if (!progress.isShowing() && !isFinishing()) progress.show();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    protected ProgressDecor createProgressView(String title, String message) {
        return new ProgressDialogWrapper(this, title, message);
    }

    @Override
    public void hideProgress() {
        if (progress == null) return;
        progress.dismiss();
        progress = null;
    }

    @Override
    public void showToast(int msgRes) {
        Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int msgRes, Object... query) {
        Toast.makeText(this, getString(msgRes, query), Toast.LENGTH_SHORT).show();
    }


    @Deprecated
    protected void setFragment(Fragment fragment) {
        setFragment(fragment, false, null);
    }

    @Deprecated
    protected void setFragment(Fragment fragment, boolean addToStack, String backStackTag) {
        getTransaction(addToStack, backStackTag).replace(getFragmentContainerId(), fragment).commit();
    }

    @Deprecated
    protected void setFragmentToBackStack(Fragment fragment) {
        setFragment(fragment, true, null);
    }

    protected FragmentTransaction getTransaction(boolean addToStack, String backStackTag) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToStack) transaction.addToBackStack(backStackTag);
        return transaction;
    }

    protected void addFragment(Fragment fragment) {
        addFragment(fragment, false, null);
    }

    protected void addFragmentToBackStack(Fragment fragment) {
        addFragment(fragment, true, null);
    }

    protected void addFragment(Fragment fragment, boolean addToStack, String backStackTag) {
        if (fragment instanceof DialogFragment) {
            ((DialogFragment) fragment).show(getSupportFragmentManager(), backStackTag);
        } else
            getTransaction(addToStack, backStackTag).add(getFragmentContainerId(), fragment).commit();
    }

    protected void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false, null);
    }

    protected void replaceFragmentToBackStack(Fragment fragment) {
        replaceFragment(fragment, true, null);
    }

    protected void replaceFragment(Fragment fragment, boolean addToStack, String backStackTag) {
        getTransaction(addToStack, backStackTag).replace(getFragmentContainerId(), fragment).commit();
    }

    protected void removeFragment(Fragment fragment, boolean addToStack, String backStackTag) {
        getTransaction(addToStack, backStackTag).remove(fragment).commit();
    }

    protected void removeFragment(Fragment fragment) {
        removeFragment(fragment, false, null);
    }
}
