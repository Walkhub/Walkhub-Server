package com.walkhub.walkhub.global.utils.code;

import net.bytebuddy.utility.RandomString;

public class CreateRandomCodeUtil {

    private CreateRandomCodeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String random() {
        return RandomString.make(5);
    }

}
