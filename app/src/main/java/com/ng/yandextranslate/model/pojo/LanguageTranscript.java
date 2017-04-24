package com.ng.yandextranslate.model.pojo;

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

    @Override
    public String toString() {
        return "LanguageTranscript{" +
                "name='" + name + '\'' +
                ", transcript='" + transcript + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LanguageTranscript that = (LanguageTranscript) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return transcript != null ? transcript.equals(that.transcript) : that.transcript == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (transcript != null ? transcript.hashCode() : 0);
        return result;
    }
}
