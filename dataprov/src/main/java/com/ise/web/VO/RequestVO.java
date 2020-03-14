package com.ise.web.VO;

import lombok.Data;

@Data
public class RequestVO {
    private String task_name;
    private String req_doc;
    private String software;
    private String state;
    private String doc_hash;
    private Long last_update;
}
