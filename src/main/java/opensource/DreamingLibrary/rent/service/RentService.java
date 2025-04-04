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
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.group.repository.GroupRepository;
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
    private final GroupRepository groupRepository;
    private final BookRepository bookRepository;

    // CREATE
    public SingleResult<Long> createRent(RentCreateRequest requestDto) {

        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
        Group group = groupRepository.findById(requestDto.groupId())
                .orElseThrow(() -> new CustomException(ErrorCode.GROUP_NOT_EXIST));
        Book book = bookRepository.findById(requestDto.bookId())
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_EXIST));

        Rent rent = RentMapper.from(requestDto, user, group, book);

        Rent saved = rentRepository.save(rent);

        saved.calculateReturnAt();

        Rent finalSaved = rentRepository.save(saved);

        return ResponseService.getSingleResult(finalSaved.getRentId());
    }

    /*
    단일 상세 읽기
     */
    public SingleResult<RentResponse> getRentById(Long rentId) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new CustomException(ErrorCode.RENT_NOT_EXIST));
        return ResponseService.getSingleResult(RentResponse.of(rent));
    }


    /*
    * 특정 그룹의 모든 대여 읽기
     */
    public ListResult<RentSummaryResponse> getAllRentsByUserAndGroup(Long userId, Long groupId) {
        List<Rent> rents = rentRepository.findAllByUser_IdAndGroup_GroupId(userId, groupId);

        List<RentSummaryResponse> rentResponseList = rents.stream()
                .map(RentSummaryResponse::of)
                .toList();

        return ResponseService.getListResult(rentResponseList);
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
