package com.linglouyi.user.utils;

import java.lang.reflect.Field;

/**
 * @author linglouyi
 */
public class MergeUtil {

    /**
     * @param sourceBean 被合并的对象bean(传入的)
     * @param targetBean 合并的对象bean(数据库查的)
     * @return targetBean 合并后的对象
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，
     * 那么sourceBean中的值会覆盖tagetBean重点的值
     */
    @SuppressWarnings("unused")
    public static <M> M combineSydwCore(M sourceBean, M targetBean) {
        Class<?> sourceBeanClass = sourceBean.getClass();
        Class<?> targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = sourceBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (sourceField.get(sourceBean) != null && !"-1".equals(sourceField.get(sourceBean)) && !"".equals(sourceField.get(sourceBean))) {
                    if ("-".equals(sourceField.get(sourceBean))) {
                        targetField.set(targetBean, "");
                    } else {
                        targetField.set(targetBean, sourceField.get(sourceBean));
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }

}
