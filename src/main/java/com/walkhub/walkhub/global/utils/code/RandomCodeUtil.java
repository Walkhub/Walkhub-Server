package com.walkhub.walkhub.global.utils.code;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang.RandomStringUtils;

public class RandomCodeUtil {

    private RandomCodeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String makeString(Integer codeLength) {
        return RandomString.make(codeLength);
    }

    public static String makeNumber(Integer codeLength) {
        return RandomStringUtils.randomNumeric(codeLength);
    }

}
