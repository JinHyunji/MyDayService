package com.web.myday.MyDay.dto;

import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String memberEmail;
    private String memberPw;
    private String memberName;
    private List<PostEntity> postEntityList = new ArrayList<>();

}
