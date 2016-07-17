// test for k&r
@isTest
public without sharing class MySimpleTest3
{
  static testMethod void testMain004()
  {
    System.assert(false, 'Hello test world!');
  }

  static void test001()
  {
    Integer i = 0;
    if (i > 3)
    {
      System.debug('pass A');
    }
    else if (i < 6)
    {
      System.debug('pass B');
    }
    return;
  }
}