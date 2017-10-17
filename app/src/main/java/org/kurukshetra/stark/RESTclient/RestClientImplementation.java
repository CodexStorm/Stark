package org.kurukshetra.stark.RESTclient;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.kurukshetra.stark.Entities.CodeMessageEntity;

public class RestClientImplementation {

    private static RequestQueue queue;

    public synchronized  static CodeMessageEntity  processVolleyError(VolleyError error){

        CodeMessageEntity codeMessageEntity = new CodeMessageEntity();

        if(error.networkResponse!=null && error.networkResponse.data!=null){
            Gson gson = new Gson();
            CodeMessageEntity errorCodeMessageEntity = gson.fromJson(new String(error.networkResponse.data), CodeMessageEntity.class);
            if (errorCodeMessageEntity.getMessage()!=null){
                codeMessageEntity.setCode(errorCodeMessageEntity.getCode());
                codeMessageEntity.setMessage(errorCodeMessageEntity.getMessage());
            }
        }else {
            try {
                if (error.getCause() !=null && error.getCause().getMessage()!=null){
                    codeMessageEntity.setMessage(error.getCause().getMessage());
                    codeMessageEntity.setCode(999);
                }
            }catch(Exception e){
                if (error.getCause() != null && error.getCause().getMessage() != null) {
                    codeMessageEntity.setMessage(error.getCause().getMessage());
                    codeMessageEntity.setCode(999);
                } else {
                    codeMessageEntity.setMessage("Error - Exception");
                    codeMessageEntity.setCode(999);
                }
            }
        }
        if(codeMessageEntity == null){
            codeMessageEntity.setCode(999);
            codeMessageEntity.setMessage("Error");
        }
        return  codeMessageEntity;
    }
}
