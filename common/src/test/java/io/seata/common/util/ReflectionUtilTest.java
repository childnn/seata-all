/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtilTest {

    //Prevent jvm from optimizing final
    // 将此值改为 常量 "hello"; 则测试会失败
    public static final String testValue = (null != null ? "hello" : "hello");

    @Test
    public void testGetClassByName() throws ClassNotFoundException {
        Assertions.assertEquals(String.class,
                ReflectionUtil.getClassByName("java.lang.String"));
    }

    @Test
    public void testGetFieldValue() throws
            NoSuchFieldException, IllegalAccessException {
        Assertions.assertEquals("d",
                ReflectionUtil.getFieldValue(new DurationUtil(), "DAY_UNIT"));

        Assertions.assertThrows(NoSuchFieldException.class,
                () -> ReflectionUtil.getFieldValue(new Object(), "A1B2C3"));
    }

    /**
     * 反射执行无参方法, 不要求 public/non-static
     */
    @Test
    public void testInvokeMethod() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Assertions.assertEquals(0, ReflectionUtil.invokeMethod("", "length"));
        Assertions.assertEquals(3,
                ReflectionUtil.invokeMethod("foo", "length"));

        Assertions.assertThrows(NoSuchMethodException.class,
                () -> ReflectionUtil.invokeMethod("", "size"));

        // 新增测试
        Object init = ReflectionUtil.invokeMethod(new ReflectionUtilTest(), "init");
        System.out.println("init = " + init);

        Object init1 = ReflectionUtil.invokeMethod(new ReflectionUtilTest(), "init1");
        System.out.println("init1 = " + init1);

    }

    static void init() {

    }

    static int init1() {
        return 0;
    }

    /**
     * 反射执行 non-static 方法, 不要求非 private.
     */
    @Test
    public void testInvokeMethod2() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Assertions.assertEquals(0, ReflectionUtil
                .invokeMethod("", "length", null, null));
        Assertions.assertEquals(3, ReflectionUtil
                .invokeMethod("foo", "length", null, null));

        Assertions.assertThrows(NoSuchMethodException.class, () -> ReflectionUtil
                .invokeMethod("", "size", null, null));
    }

    /**
     * 反射执行 static 方法, 包括父类/接口
     */
    @Test
    public void testInvokeMethod3() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Assertions.assertEquals("0", ReflectionUtil.invokeStaticMethod(
                String.class, "valueOf",
                new Class<?>[]{int.class}, new Object[]{0}));
        Assertions.assertEquals("123", ReflectionUtil.invokeStaticMethod(
                String.class, "valueOf",
                new Class<?>[]{int.class}, new Object[]{123}));

        Assertions.assertThrows(NoSuchMethodException.class, () -> ReflectionUtil
                .invokeStaticMethod(String.class, "size", null, null));
    }

    /**
     * @see ReflectionUtil#getMethod(Class, String, Class[])  获取指定 Method 对象
     */
    @Test
    public void testGetMethod() throws NoSuchMethodException {
        Assertions.assertEquals("public int java.lang.String.length()",
                ReflectionUtil.getMethod(String.class, "length", null)
                        .toString());
        Assertions.assertEquals("public char java.lang.String.charAt(int)",
                ReflectionUtil.getMethod(String.class, "charAt",
                        new Class<?>[]{int.class}).toString());

        Assertions.assertThrows(NoSuchMethodException.class,
                () -> ReflectionUtil.getMethod(String.class, "size", null));
    }

    /**
     * @see ReflectionUtil#getInterfaces(Class) 获取指定类实现的所有 父接口, 递归
     */
    @Test
    public void testGetInterfaces() {
        Assertions.assertArrayEquals(new Object[]{Serializable.class},
                ReflectionUtil.getInterfaces(Serializable.class).toArray());

        Assertions.assertArrayEquals(new Object[]{
                        Serializable.class, Comparable.class, CharSequence.class},
                ReflectionUtil.getInterfaces(String.class).toArray());
    }

    /**
     * 修改类的 final 属性值
     * 注意, 此方法并不能修改真正的常量, 如 final String = "";
     */
    @Test
    public void testModifyStaticFinalField() throws NoSuchFieldException, IllegalAccessException {
        Assertions.assertEquals("hello", testValue);
        ReflectionUtil.modifyStaticFinalField(ReflectionUtilTest.class, "testValue", "hello world");
        Assertions.assertEquals("hello world", testValue);
    }
}
