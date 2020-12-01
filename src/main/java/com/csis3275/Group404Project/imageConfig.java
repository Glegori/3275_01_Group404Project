package com.csis3275.Group404Project;

import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
*
* Specify and enable the folder where the bill images will be stored
*
*/
@Configuration
public class imageConfig implements WebMvcConfigurer {
 
	/**
	 * @param ResourceHandlerRegistry registry
     * This method call exposeDirectory method
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("uploadPhotos", registry);
    }
     
    /**
     * exposeDirectory method allows to uploadPhotos to be readed by the system
     * @param String dirName which is the name of the directory "uploadPhotos"
     * ResourceHandlerRegistry registry
     */
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:"+ uploadPath + "/");
    }
}
