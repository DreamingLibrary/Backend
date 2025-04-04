package opensource.DreamingLibrary.global.service;

import opensource.DreamingLibrary.global.dto.response.result.ListResult;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseService {
    //단일값 감싸는 메서드
    public static <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        return result;
    }

    //리스트 값을 감싸는 메서드
    public static <T> ListResult<T> getListResult(List<T> list){
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        return result;
    }
}
