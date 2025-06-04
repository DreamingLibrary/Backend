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
import opensource.DreamingLibrary.user.service.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public SuccessResponse<SingleResult<Long>> createRent(
            @Valid @RequestBody RentCreateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        SingleResult<Long> result = rentService.createRent(request, userDetails.getId());
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
     * 현재 로그인한 사용자의 모든 대출 정보 조회
     */
    @GetMapping("/my")
    @Operation(summary = "현재 로그인한 사용자의 모든 대출 정보 조회")
    public SuccessResponse<ListResult<RentSummaryResponse>> getMyRents(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        ListResult<RentSummaryResponse> result = rentService.getMyRents(userDetails.getId());
        return SuccessResponse.ok(result);
    }

    /**
     * 책 반납 (해당 rent record를 삭제)
     */
    @DeleteMapping("/{rentId}")
    @Operation(summary = "책 반납")
    public SuccessResponse<SingleResult<String>> returnRent(@PathVariable("rentId") Long rentId){
        SingleResult<String> result = rentService.returnRent(rentId);
        return SuccessResponse.ok(result);
    }
}
