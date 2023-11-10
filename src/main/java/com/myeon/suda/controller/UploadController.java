package com.myeon.suda.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.myeon.suda.dto.UploadResultDTO;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;



@RestController
@Log4j2
public class UploadController {
    @Value("${com.myeon.upload.path}")
    private String upload_path;

    @PostMapping("/uploadAjax")
    public ResponseEntity<Map<String,String>> uploadFile(MultipartFile upload){
        String original_name = upload.getOriginalFilename();
        String file_name = original_name.substring(original_name.lastIndexOf("\\")+1);
        log.info("file name :"+file_name);

        String folder_path=make_folder();
        String uuid=UUID.randomUUID().toString();
        log.info("====file seperator===");
        log.info(File.separator);
        String save_name=upload_path+File.separator+folder_path+File.separator+uuid+"_"+file_name;
        Path savePath=Paths.get(save_name);
        try{
            upload.transferTo(savePath);

        }catch(IOException e){
            e.printStackTrace();
        }
        // String save_file = folder_path + File.separator + uuid + "_" + file_name;
        String save_file="";
        try{
            save_file=  URLEncoder.encode(folder_path+File.separator+uuid+"_"+file_name, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }


        String url="/display?file_name="+save_file;
        Map<String, String> map = new HashMap<String, String>();
        map.put("uploaded", "true");
        map.put("url",url);


        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/upload_ajax")
    public ResponseEntity<List<UploadResultDTO>> upload_file(MultipartFile[] upload_files){
        List<UploadResultDTO> resultDTO_list = new ArrayList<>();
        for(MultipartFile upload_file : upload_files){
            if(upload_file.getContentType().startsWith("image") == false){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String original_name = upload_file.getOriginalFilename();
            String file_name = original_name.substring(original_name.lastIndexOf("\\")+1);
            String folder_path = make_folder();
            String uuid = UUID.randomUUID().toString();
            String save_name = upload_path + File.separator + folder_path + File.separator + uuid + "_" + file_name;
            Path save_path = Paths.get(save_name);
            try{
                upload_file.transferTo(save_path);
                String thumbnail_save_name =  upload_path + File.separator + folder_path + File.separator + "s_" + uuid + "_" + file_name;
                File thumbnail_file = new File(thumbnail_save_name);
                Thumbnailator.createThumbnail(save_path.toFile(), thumbnail_file, 100, 100);
                resultDTO_list.add(new UploadResultDTO(file_name, uuid, folder_path));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resultDTO_list, HttpStatus.OK);
    }

    private String make_folder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folder_path = str.replace("//", File.separator);
        File upload_path_folder = new File(upload_path, folder_path);
        if(upload_path_folder.exists() == false){
            upload_path_folder.mkdirs();
        }
        return folder_path;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> get_file(String file_name){
        ResponseEntity<byte[]> result = null;
        try{
            String src_file_name = URLDecoder.decode(file_name, "UTF-8");
            log.info(src_file_name);
            File file = new File(upload_path + File.separator + src_file_name);
            log.info(file);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/remove_file")
    public ResponseEntity<Boolean> remove_file(String file_name){
        String src_file_name = null;
        try{
            src_file_name = URLDecoder.decode(file_name, "UTF-8");
            File file = new File(upload_path + File.separator + src_file_name);
            boolean result = file.delete();

            File thumbnail =new File(file.getParent(), "s_"+file.getName());
            result = thumbnail.delete();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
