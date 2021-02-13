package com.lvj.utilsdemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JavaTestDemo {
    @Test
    public void test() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("pos = " + i);
        }
        System.out.println("list = " + list.toString());
        List<String> stringList = list.subList(2, 4);
        ArrayList<String> test = new ArrayList<>(stringList);
        System.out.println("test = " + test.toString());

    }

}
