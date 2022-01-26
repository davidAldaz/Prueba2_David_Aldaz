package com.nadershamma.apps.eventhandlers_DIAS;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.widget.Toast;

import com.nadershamma.apps.androidfunwithflags.MainActivity_DIAS;
import com.nadershamma.apps.androidfunwithflags.R;

import java.util.Set;

public class PreferenceChangeListener_DIAS implements OnSharedPreferenceChangeListener {
    private MainActivity_DIAS mainActivityDIAS;

    public PreferenceChangeListener_DIAS(MainActivity_DIAS mainActivityDIAS) {
        this.mainActivityDIAS = mainActivityDIAS;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.mainActivityDIAS.setPreferencesChanged(true);

        if (key.equals(this.mainActivityDIAS.getREGIONS())) {
            this.mainActivityDIAS.getQuizViewModel().setGuessRows(sharedPreferences.getString(
                    MainActivity_DIAS.CHOICES, null));
            this.mainActivityDIAS.getQuizFragment().resetQuiz();
        } else if (key.equals(this.mainActivityDIAS.getCHOICES())) {
            Set<String> regions = sharedPreferences.getStringSet(this.mainActivityDIAS.getREGIONS(),
                    null);
            if (regions != null && regions.size() > 0) {
                this.mainActivityDIAS.getQuizViewModel().setRegionsSet(regions);
                this.mainActivityDIAS.getQuizFragment().resetQuiz();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                regions.add(this.mainActivityDIAS.getString(R.string.default_region));
                editor.putStringSet(this.mainActivityDIAS.getREGIONS(), regions);
                editor.apply();
                Toast.makeText(this.mainActivityDIAS, R.string.default_region_message,
                        Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(this.mainActivityDIAS, R.string.restarting_quiz,
                Toast.LENGTH_SHORT).show();
    }
}
