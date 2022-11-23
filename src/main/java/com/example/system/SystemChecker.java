package com.example.system;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 端末情報をコンソールに出力する
 */
public class SystemChecker {
    public static void main(String[] args) {
        printHostName();
        print("java.version : " + System.getProperty("java.version"));
        print("java.version.date : " + System.getProperty("java.version.date"));
        print("java.vendor : " + System.getProperty("java.vendor"));
        print("java.vendor.url : " + System.getProperty("java.vendor.url"));
        print("java.vendor.version : " + System.getProperty("java.vendor.version"));
        print("java.home : " + System.getProperty("java.home"));
        print("java.vm.specification.version : " + System.getProperty("java.vm.specification.version"));
        print("java.vm.specification.vendor : " + System.getProperty("java.vm.specification.vendor"));
        print("java.vm.specification.name : " + System.getProperty("java.vm.specification.name"));
        print("java.vm.version : " + System.getProperty("java.vm.version"));
        print("java.vm.vendor : " + System.getProperty("java.vm.vendor"));
        print("java.vm.name : " + System.getProperty("java.vm.name"));
        print("java.specification.version : " + System.getProperty("java.specification.version"));
        print("java.specification.vendor : " + System.getProperty("java.specification.vendor"));
        print("java.specification.name : " + System.getProperty("java.specification.name"));
        print("java.class.version : " + System.getProperty("java.class.version"));
        print("java.class.path : " + System.getProperty("java.class.path"));
        print("java.library.path : " + System.getProperty("java.library.path"));
        print("java.io.tmpdir : " + System.getProperty("java.io.tmpdir"));
        print("java.compiler : " + System.getProperty("java.compiler"));
        print("os.name : " + System.getProperty("os.name"));
        print("os.arch : " + System.getProperty("os.arch"));
        print("os.version : " + System.getProperty("os.version"));
        print("file.separator : " + System.getProperty("file.separator"));
        print("path.separator : " + System.getProperty("path.separator"));
        print("line.separator : " + System.getProperty("line.separator"));
        print("user.name : " + System.getProperty("user.name"));
        print("user.home : " + System.getProperty("user.home"));
        print("user.dir : " + System.getProperty("user.dir"));
        print("native.encoding : " + System.getProperty("native.encoding"));
    }

    private static void printHostName() {

        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String hostName2 = Inet6Address.getLocalHost().getHostAddress();
            print("Host Name : " + hostName);
            print("Host Name : " + hostName2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
