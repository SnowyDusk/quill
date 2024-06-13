package com.quill.api.identity.constant;

/**
 * @author: tuberose
 * @date: 2024/6/1 18:13
 */
public class UserIdentity {

    public static final int READER = 1;
    public static final int PRINTER = 2;
    public static final int AUTHOR = 4;
    public static final int PUBLISHER = 8;
    public static final int ADMINISTRATOR = 16;

    public static boolean readerDiscriminator(int identity) {
        return (identity & READER) == READER;
    }

    public static boolean printerDiscriminator(int identity) {
        return (identity & PRINTER) == PRINTER;
    }

    public static boolean authorDiscriminator(int identity) {
        return (identity & AUTHOR) == AUTHOR;
    }

    public static boolean publisherDiscriminator(int identity) {
        return (identity & PUBLISHER) == PUBLISHER;
    }

    public static boolean administratorDiscriminator(int identity) {
        return (identity & ADMINISTRATOR) == ADMINISTRATOR;
    }
}
