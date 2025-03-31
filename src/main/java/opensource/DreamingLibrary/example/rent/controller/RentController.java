package opensource.DreamingLibrary.example.rent.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.rent.dto.request.RentCreateRequest;
import opensource.DreamingLibrary.example.rent.dto.response.RentResponse;
import opensource.DreamingLibrary.example.rent.service.RentService;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    public SuccessResponse<SingleResult<RentResponse>> createRent(@RequestBody RentCreateRequest request) {
        RentResponse rentResponse = rentService.createRent(request);

        SingleResult<RentResponse> singleResult = new SingleResult<>();
        singleResult.setData(rentResponse);

        return SuccessResponse.ok(singleResult);
    }

    @GetMapping("/{rentId}")
    public SuccessResponse<SingleResult<RentResponse>> getRent(@PathVariable Long rentId) {
        RentResponse rentResponse = rentService.getRent(rentId);

        SingleResult<RentResponse> singleResult = new SingleResult<>();
        singleResult.setData(rentResponse);

        return SuccessResponse.ok(singleResult);
    }

    // 추후 update, delete, list 조회 등 추가
}
