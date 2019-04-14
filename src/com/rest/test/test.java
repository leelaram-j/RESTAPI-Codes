package com.rest.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class test
{
    public static void main(String[] args) throws IOException
    {
        System.out.println(System.getProperty("user.dir"));
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("//Users//lee//Documents//RestAssuredDemo//env.properties");
        prop.load(fis);
        System.out.println(prop.getProperty("basePath"));
    }
}
