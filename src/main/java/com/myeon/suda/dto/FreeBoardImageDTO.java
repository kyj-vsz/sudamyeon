package com.myeon.suda.dto;

import java.net.URLEncoder;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardImageDTO {
    private Long free_board_inum;

    private Long free_board_no;

    private String free_board_img_name;

    private String free_board_path;

    private String free_board_uuid;

    private LocalDateTime regdate, moddate;

    public String get_image_url(){
        try{
            return URLEncoder.encode(free_board_path+"/"+free_board_uuid+"_"+free_board_img_name, "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public String get_thumbnail_url(){
        try{
            return URLEncoder.encode(free_board_path+"/s_"+free_board_uuid+"_"+free_board_img_name, "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
