package nl.koen02.failz.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private int counter;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Button pressed 0 times");
        counter = 0;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setTextButtonClicked() {
        this.counter++;
        mText.setValue(String.format(Locale.getDefault(), "Button pressed %d times", this.counter));
    }
}