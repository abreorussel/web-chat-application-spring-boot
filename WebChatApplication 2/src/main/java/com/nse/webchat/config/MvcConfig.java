package com.nse.webchat.config;

import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class MvcConfig implements WebMvcConfigurer 
{ 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	exposeDirectory("src/main/resources/static/images", registry);
    	exposeDirectory("src/main/resources/static/attachment", registry);
    }
     
    private void exposeDirectory(String folderName, ResourceHandlerRegistry registry) 
    {
        Path uploadFolder = Paths.get(folderName);
        String uploadPath = uploadFolder.toFile().getAbsolutePath();
        if (folderName.startsWith("../")) 
        	folderName = folderName.replace("../", "");
        registry.addResourceHandler("/" + folderName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
}
