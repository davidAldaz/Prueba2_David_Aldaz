package com.nadershamma.apps.eventhandlers_DIAS;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nadershamma.apps.androidfunwithflags.MainActivityFragment_DIAS;
import com.nadershamma.apps.androidfunwithflags.R;
import com.nadershamma.apps.androidfunwithflags.ResultsDialogFragment_DIAS;
import com.nadershamma.apps.lifecyclehelpers_DIAS.QuizViewModel_DIAS;

public class GuessButtonListener_DIAS implements OnClickListener {
    private MainActivityFragment_DIAS mainActivityFragmentDIAS;
    private Handler handler;

    public GuessButtonListener_DIAS(MainActivityFragment_DIAS mainActivityFragmentDIAS) {
        this.mainActivityFragmentDIAS = mainActivityFragmentDIAS;
        this.handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        Button guessButton = ((Button) v);
        String guess = guessButton.getText().toString();
        String answer = this.mainActivityFragmentDIAS.getQuizViewModel().getCorrectCountryName();
        this.mainActivityFragmentDIAS.getQuizViewModel().setTotalGuesses(1);

        if (guess.equals(answer)) {
            this.mainActivityFragmentDIAS.getQuizViewModel().setCorrectAnswers(1);
            this.mainActivityFragmentDIAS.getAnswerTextView().setText(answer + "!");
            this.mainActivityFragmentDIAS.getAnswerTextView().setTextColor(
                    this.mainActivityFragmentDIAS.getResources().getColor(R.color.correct_answer));

            this.mainActivityFragmentDIAS.disableButtons();

            if (this.mainActivityFragmentDIAS.getQuizViewModel().getCorrectAnswers()
                    == QuizViewModel_DIAS.getFlagsInQuiz()) {
                ResultsDialogFragment_DIAS quizResults = new ResultsDialogFragment_DIAS();
                quizResults.setCancelable(false);
                try {
                    quizResults.show(this.mainActivityFragmentDIAS.getChildFragmentManager(), "Quiz Results");
                } catch (NullPointerException e) {
                    Log.e(QuizViewModel_DIAS.getTag(),
                            "GuessButtonListener: this.mainActivityFragment.getFragmentManager() " +
                                    "returned null",
                            e);
                }
            } else {
                this.handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                mainActivityFragmentDIAS.animate(true);
                            }
                        }, 2000);
            }
        } else {
            this.mainActivityFragmentDIAS.incorrectAnswerAnimation();
            guessButton.setEnabled(false);
        }
    }
}
