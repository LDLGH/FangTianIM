package com.ldl.module.imkit.emoji;

import androidx.annotation.NonNull;

/**
 * Describe: 表情的实体类
 */

public class EmojiBean {
    private String id;
    private int unicodeInt;

    public String getEmojiString() {
        return getEmojiStringByUnicode(unicodeInt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnicodeInt() {
        return getEmojiStringByUnicode(unicodeInt);
    }

    public int getRes() {
        return unicodeInt;
    }

    public void setUnicodeInt(int unicodeInt) {
        this.unicodeInt = unicodeInt;
    }

    public static String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    @NonNull
    @Override
    public String toString() {
        return "EmojiBean{" +
                "id=" + id +
                ", unicodeInt=" + unicodeInt +
                '}';
    }
}
