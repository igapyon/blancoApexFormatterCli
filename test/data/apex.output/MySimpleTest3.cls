@isTest
public without sharing class MySimpleTest3 {
    static testMethod void testMain003() {
        System.assert(false, 'Hello test world!');
    }

    static void test001() {
        Integer i = 0;
        if (i > 3) {
            System.debug('pass A');
        } else {
            System.debug('pass B');
        }
        return;
    }
}