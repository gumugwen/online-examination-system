package com.hw.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 使用第三方Kaptcha来生成图形验证码
 */
@Configuration
public class KaptchaImageCodeConfig {


    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "192,192,192");
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "170");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "50");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "40");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "5");
        // 图片效果
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
