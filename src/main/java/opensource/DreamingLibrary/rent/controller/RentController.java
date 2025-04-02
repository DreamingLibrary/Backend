package opensource.DreamingLibrary.rent.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.book.dto.response.BookResponse;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.ListResult;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.rent.dto.request.RentCreateRequest;
import opensource.DreamingLibrary.rent.dto.response.RentResponse;
import opensource.DreamingLibrary.rent.dto.response.RentSummaryResponse;
import opensource.DreamingLibrary.rent.service.RentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rents")
@RequiredArgsConstructor
@Tag(name = "대여(Rent)")
public class RentController {

    private final RentService rentService;

    /**
     * 대여 생성
     */
    @PostMapping
    @Operation(summary = "대여하기")
    public SuccessResponse<SingleResult<Long>> createRent(@Valid @RequestBody RentCreateRequest request) {
        SingleResult<Long> result = rentService.createRent(request);
        return SuccessResponse.ok(result);
    }

    /**
     * 대여 단건 조회
     */
    @GetMapping("/{rentId}")
    @Operation(summary = "대여 단건 조회")
    public SuccessResponse<SingleResult<RentResponse>> getRentById(@PathVariable("rentId") Long rentId) {
        SingleResult<RentResponse> result = rentService.getRentById(rentId);
        return SuccessResponse.ok(result);
    }

    /**
     * 속한 그룹 속 유저가 빌린 렌트 총 조회
     */

    @GetMapping("/search")
    @Operation(summary = "유저 + 그룹 기반 대여 목록 조회")
    public SuccessResponse<ListResult<RentSummaryResponse>> getAllRentsByUserAndGroup(
            @RequestParam("userId") Long userId,
            @RequestParam("groupId") Long groupId
    ) {
        ListResult<RentSummaryResponse> result = rentService.getAllRentsByUserAndGroup(userId, groupId);

        return SuccessResponse.ok(result);
    }


}
