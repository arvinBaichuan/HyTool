package com.baichuan.hy.tool.util.translator;

public class TranslatorFactory {
    public enum TranslatorType {
        GOOGLE,
        MICROSOFT,
        YOUDAO
    }

    public static Translator getTranslator(TranslatorType type) {
        switch (type) {
            case GOOGLE:
                return new GoogleTranslatorUtil();
            case MICROSOFT:
//                return new MicrosoftTranslatorUtil();
            case YOUDAO:
                return new YoudaoTranslatorUtil();
            default:
                throw new IllegalArgumentException("Unknown translator type: " + type);
        }
    }
}
