package com.myeon.suda.dto;

import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long inum;
    
    private Long mno;

    private String img_name;

    private String path;

    private String uuid;

    public String get_image_url(){
        try{
            return URLEncoder.encode(path+"/"+uuid+"_"+img_name, "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public String get_thumbnail_url(){
        try{
            return URLEncoder.encode(path+"/s_"+uuid+"_"+img_name, "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
