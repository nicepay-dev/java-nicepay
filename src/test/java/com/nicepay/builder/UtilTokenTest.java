package com.nicepay.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nicepay.builder.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

//@SpringBootTest(classes = UtilApplication.class)
class UtilTokenTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void createToken(){
        TokenUtil token = applicationContext.getBean("utilTokenApp", TokenUtil.class);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String jsonRequest = gson.toJson(token);
        System.out.println(jsonRequest);
    }

}