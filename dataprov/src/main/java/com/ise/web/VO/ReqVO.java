package com.ise.web.VO;

import lombok.Data;

@Data
public class ReqVO {
    String requester;
    String re_doc_name;
    String test_app_name;
    Long re_submit_date;
}
