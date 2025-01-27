package com.sevtinge.cemiuiler.ui.fragment.systemui;

import static com.sevtinge.cemiuiler.utils.api.LinQiqiApisKt.isDeviceEncrypted;
import static com.sevtinge.cemiuiler.utils.devicesdk.SystemSDKKt.isAndroidR;

import android.view.View;

import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.BaseSettingsActivity;
import com.sevtinge.cemiuiler.ui.fragment.base.SettingsPreferenceFragment;

import moralnorm.preference.SwitchPreference;

public class LockScreenSettings extends SettingsPreferenceFragment {
    SwitchPreference mBlurButton; // 锁屏按钮背景模糊
    SwitchPreference mForceSystemFonts; // 时钟使用系统字体
    SwitchPreference mPasswordFree; // 开机免输入密码

    @Override
    public int getContentResId() {
        return R.xml.system_ui_lock_screen;
    }

    @Override
    public void initPrefs() {
        mBlurButton = findPreference("prefs_key_system_ui_lock_screen_blur_button");
        mForceSystemFonts = findPreference("prefs_key_system_ui_lock_screen_force_system_fonts");
        mPasswordFree = findPreference("prefs_key_system_ui_lock_screen_password_free");

        mBlurButton.setVisible(!isAndroidR());
        mForceSystemFonts.setVisible(!isAndroidR());

        if (isDeviceEncrypted(getContext())) {
            mPasswordFree.setEnabled(false);
            mPasswordFree.setSummary(R.string.system_ui_lock_screen_password_free_tip);
        }
    }

    @Override
    public View.OnClickListener addRestartListener() {
        return view -> ((BaseSettingsActivity)getActivity()).showRestartDialog(
            getResources().getString(R.string.system_ui),
            "com.android.systemui"
        );
    }
}
