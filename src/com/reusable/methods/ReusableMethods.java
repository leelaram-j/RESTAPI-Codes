package com.reusable.methods;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods
{
    public static JsonPath toJson(Response res)
    {
        String s = res.asString();
        JsonPath js = new JsonPath(s);
        return js;
    }

    public static XmlPath toXML(Response res)
    {
        String s = res.asString();
        XmlPath xp = new XmlPath(s);
        return xp;
    }
}
