package opensource.DreamingLibrary.example.rent.service;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.book.entity.Book;
import opensource.DreamingLibrary.example.book.repository.BookRepository;
import opensource.DreamingLibrary.example.group.entity.Group;
import opensource.DreamingLibrary.example.group.repository.GroupRepository;
import opensource.DreamingLibrary.example.rent.dto.request.RentCreateRequest;
import opensource.DreamingLibrary.example.rent.dto.response.RentResponse;
import opensource.DreamingLibrary.example.rent.entity.Rent;
import opensource.DreamingLibrary.example.rent.mapper.RentMapper;
import opensource.DreamingLibrary.example.rent.repository.RentRepository;
import opensource.DreamingLibrary.example.user.entity.User;
import opensource.DreamingLibrary.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final BookRepository bookRepository;

    // CREATE
    public RentResponse createRent(RentCreateRequest requestDto) {

        // 1) user, group, book 조회 (필수값)
        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found with id=" + requestDto.userId()));
        Group group = groupRepository.findById(requestDto.groupId())
                .orElseThrow(() -> new RuntimeException("Group not found with id=" + requestDto.groupId()));
        Book book = bookRepository.findById(requestDto.bookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id=" + requestDto.bookId()));

        // 2) 매퍼 사용 또는 직접 빌드
        Rent rent = RentMapper.from(requestDto, user, group, book);

        Rent saved = rentRepository.save(rent);

        // 3) 응답 변환
        return toResponse(saved);
    }

    // READ
    public RentResponse getRent(Long rentId) {
        Optional<Rent> optionalRent = rentRepository.findById(rentId);
        return optionalRent
                .map(this::toResponse)
                .orElse(null); // 실제로는 예외 처리 or 적절한 에러 반환
    }

    // 엔티티 -> 응답 DTO 변환
    private RentResponse toResponse(Rent rent) {
        return RentResponse.builder()
                .rentId(rent.getRentId())
                .userId(rent.getUser().getId())
                .groupId(rent.getGroup().getGroupId())
                .bookId(rent.getBook().getBookId())
                .rentalPeriod(rent.getRentalPeriod())
                .returnAt(rent.getReturnAt())
                .rentalCount(rent.getRentalCount())
                .createdAt(rent.getCreatedAt())
                .updatedAt(rent.getUpdatedAt())
                .build();
    }
}
