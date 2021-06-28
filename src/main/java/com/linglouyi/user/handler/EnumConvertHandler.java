package com.linglouyi.user.handler;

import com.linglouyi.user.constant.base.EnumBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @author linglouyi
 */
@Component
@Slf4j
public class EnumConvertHandler implements ConverterFactory<String, EnumBase> {

    @Override
    public <T extends EnumBase> Converter<String, T> getConverter(Class<T> aClass) {
        return new IntegerToEnum<>(aClass);
    }


    @SuppressWarnings("all")
    private static class IntegerToEnum<T extends EnumBase>  implements Converter<String, T> {
        private final Class<T> targerType;
        /**
         * Instantiates a new String to i eum.
         *
         * @param targerType the targer type
         */
        public IntegerToEnum(Class<T> targerType) {
            this.targerType = targerType;
        }

        @Override
        public T convert(String source) {
            if ("".equals(source)) {
                return null;
            }
            return (T) getEnum(targerType,source);
        }

        public static <T extends EnumBase> Object getEnum(Class<T> targerType, String source) {
            for (T enumObj : targerType.getEnumConstants()) {
                if (source.equals(String.valueOf(enumObj.getOrdinal()))) {
                    return enumObj;
                }
            }
            return null;
        }
    }

}
