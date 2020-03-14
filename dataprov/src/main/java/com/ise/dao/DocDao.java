package com.ise.dao;

import cn.iselab.bcclient.model.Doc;
import java.lang.reflect.Type;
import java.util.List;

public interface DocDao {
    Doc createDoc(String docId,Doc doc);
    Doc getLatestDocById(String docId, Type type);
    List<Doc> getDocByKV(String key, String value, Type type);
    List<Doc> getAllDoc(Type type);
}
