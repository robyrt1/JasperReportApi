package net.jasper.shared;

import java.io.FileNotFoundException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class UtilsShared {

    private final ResourceLoader resourceLoader;


    public UtilsShared( ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    public String getAbsolutePath(String Path, String ARQJRXML) throws FileNotFoundException{
        Resource resource = resourceLoader.getResource(Path + ARQJRXML);
        try {
            return resource.getFile().getAbsolutePath();
        } catch (Exception e) {
            throw new FileNotFoundException("Arquivo não encontrado: " + Path + ARQJRXML);
        }
    }
}
