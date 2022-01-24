package com.walkhub.walkhub.global.utils.code;

import net.bytebuddy.utility.RandomString;

public class CreateRandomCodeUtil {

    public static String random() {
        return RandomString.make(5);
    }

}
