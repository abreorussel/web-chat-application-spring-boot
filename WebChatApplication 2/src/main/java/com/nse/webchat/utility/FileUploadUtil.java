package com.nse.webchat.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;
 
public class FileUploadUtil {
     
    public void saveFile(String uploadFolder, String fileName, MultipartFile multipartFile) throws IOException 
    {
        Path uploadPath = Paths.get(uploadFolder);
        if (!Files.exists(uploadPath)) 
        {
            Files.createDirectories(uploadPath);
        }
        if(!fileName.equals("default.png"))
        {
        	try (InputStream inputStream = multipartFile.getInputStream()) 
            {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } 
            catch (IOException ex) 
            {        
                throw new IOException("Could not save Profile Picture " + fileName, ex);
            }
        }
        else
        {
        	File defaultPic = new File("src/main/resources/static/default.png");
        	try(InputStream inpStream = new FileInputStream(defaultPic))
        	{
        		Path filePath = uploadPath.resolve(fileName);
                Files.copy(inpStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        	}
        	catch (IOException ex) 
            {        
                throw new IOException("Default Photo Not Found" + fileName, ex);
            }
        }
              
    }
    
    public void saveAttachment(String uploadFolder, String fileName, MultipartFile multipartFile) throws IOException 
    {
        Path uploadPath = Paths.get(uploadFolder);
        
        if (!Files.exists(uploadPath)) 
        {        	
            Files.createDirectories(uploadPath);
        }
       
    	try (InputStream inputStream = multipartFile.getInputStream()) 
        {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } 
        catch (IOException ex) 
        {        
            throw new IOException("Could not save attachment " + fileName, ex);
        }
        
        
              
    }
}