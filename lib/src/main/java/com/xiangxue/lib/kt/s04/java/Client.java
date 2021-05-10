package com.xiangxue.lib.kt.s04.java;

import java.io.File;
import java.io.FileReader;
import kotlin.io.FilesKt;
import kotlin.text.Charsets;

public class Client {

    void test() {
        File file = new File("xxxx");

        // Java 调用 扩展函数
        FilesKt.readText(file, Charsets.UTF_8);

    }

}
