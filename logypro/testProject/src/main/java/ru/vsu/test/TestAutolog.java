package ru.vsu.test;

import ru.vsu.diplom.service.processors.AutoLog;

@AutoLog()
public class TestAutolog {
    TestAutolog() {

    }

    protected Integer test(String[] args) {
        System.out.println("");
        return 1;
    }
    public void test1(String[] args) {

    }
    protected Integer test2(String[] args, Integer abdya) {

        return abdya;
    }

    private void test3(String[] args, Integer abdya) {

    }
    void test4(String[] args, Integer abdya) {

    }
}
