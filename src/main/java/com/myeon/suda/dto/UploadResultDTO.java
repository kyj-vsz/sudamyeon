package com.myeon.suda.dto;

import java.io.Serializable;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    private String file_name;

    private String uuid;

    private String folder_path;

    public String get_image_url(){
        try{
            return URLEncoder.encode(folder_path+"/"+uuid+"_"+file_name, "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public String get_thumbnail_url(){
        try{
            return URLEncoder.encode(folder_path+"/s"+uuid+"_"+file_name, "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
