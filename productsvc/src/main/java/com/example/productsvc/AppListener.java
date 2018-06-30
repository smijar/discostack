package com.example.productsvc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ApplicationEvent;


public class AppListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("event: " + event);
    }
  }
  