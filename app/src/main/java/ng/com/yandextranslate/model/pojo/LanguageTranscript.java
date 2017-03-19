package ng.com.yandextranslate.model.pojo;

/**
 * Created by NG on 19.03.17.
 */

public class LanguageTranscript {
    private String name;
    private String transcript;

    public LanguageTranscript(String name, String transcript) {
        this.name = name;
        this.transcript = transcript;
    }

    public String getName() {
        return name;
    }

    public String getTranscript() {
        return transcript;
    }
}
