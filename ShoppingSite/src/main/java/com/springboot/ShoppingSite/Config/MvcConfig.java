package com.springboot.ShoppingSite.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path itemUploadDir = Paths.get("./item-images");
        String itemUploadPath = itemUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/item-images/**")
                .addResourceLocations("file:/" + itemUploadPath + "/");



    }
}
