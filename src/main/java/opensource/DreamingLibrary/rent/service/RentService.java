package opensource.DreamingLibrary.rent.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.book.dto.response.BookResponse;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.book.repository.BookRepository;
import opensource.DreamingLibrary.global.dto.response.result.ListResult;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import opensource.DreamingLibrary.global.exception.CustomException;
import opensource.DreamingLibrary.global.exception.ErrorCode;
import opensource.DreamingLibrary.global.service.ResponseService;
import opensource.DreamingLibrary.rent.dto.request.RentCreateRequest;
import opensource.DreamingLibrary.rent.dto.response.RentResponse;
import opensource.DreamingLibrary.rent.dto.response.RentSummaryResponse;
import opensource.DreamingLibrary.rent.entity.Rent;
import opensource.DreamingLibrary.rent.mapper.RentMapper;
import opensource.DreamingLibrary.rent.repository.RentRepository;
import opensource.DreamingLibrary.user.entity.User;
import opensource.DreamingLibrary.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    // CREATE
    public Long createRent(RentCreateRequest requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
        Book book = bookRepository.findById(requestDto.bookId())
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_EXIST));

        Rent rent = RentMapper.from(requestDto, user, book);

        Rent saved = rentRepository.save(rent);

        saved.calculateReturnAt();

        Rent finalSaved = rentRepository.save(saved);

        return finalSaved.getRentId();
    }

    /*
    단일 상세 읽기
     */
    public RentResponse getRentById(Long rentId) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new CustomException(ErrorCode.RENT_NOT_EXIST));
        return RentResponse.of(rent);
    }

    /**
     * 현재 로그인한 사용자의 모든 대출 정보 조회
     */
    public List<RentSummaryResponse> getMyRents(Long userId) {
        // 사용자 존재 여부만 확인
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorCode.USER_NOT_EXIST);
        }

        // 사용자의 모든 대출 정보 조회
        List<Rent> rents = rentRepository.findAllByUser_Id(userId);
        List<RentSummaryResponse> rentResponseList = rents.stream()
                .map(RentSummaryResponse::of)
                .toList();

        return rentResponseList;
    }

    /**
     * 책 반납
     */
    public SingleResult<String> returnRent(Long rentId){
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new CustomException(ErrorCode.RENT_NOT_EXIST));

        rentRepository.delete(rent);
        return ResponseService.getSingleResult("반납 완료");
    }

}
