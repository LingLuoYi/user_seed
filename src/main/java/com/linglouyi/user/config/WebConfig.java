package com.linglouyi.user.config;

import com.linglouyi.user.handler.EnumConvertHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author linglouyi
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private EnumConvertHandler enumConvertFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(enumConvertFactory);
    }
}

