package com.walkhub.walkhub.global.utils.code;

import net.bytebuddy.utility.RandomString;

public class RandomCodeUtil {

    private RandomCodeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String make() {
        return RandomString.make(5);
    }

}
