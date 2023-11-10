package com.myeon.suda.dto;



import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FreeBoardReplyPageDTO {
    private int reply_count;
    private List<FreeBoardReplyDTO> list;
}
