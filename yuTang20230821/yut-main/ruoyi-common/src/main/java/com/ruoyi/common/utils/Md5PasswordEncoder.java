package com.ruoyi.common.utils;

import com.ruoyi.common.utils.sign.Md5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author chenshijie
 * @date 2023/4/21 16:26
 */

public class Md5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return Md5Utils.hash(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Md5Utils.hash(rawPassword.toString()).equals(encodedPassword);
    }
}

