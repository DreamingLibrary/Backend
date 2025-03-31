package opensource.DreamingLibrary.example.rent.mapper;

import opensource.DreamingLibrary.example.rent.dto.request.RentCreateRequest;
import opensource.DreamingLibrary.example.rent.entity.Rent;
import opensource.DreamingLibrary.example.user.entity.User;
import opensource.DreamingLibrary.example.group.entity.Group;
import opensource.DreamingLibrary.example.book.entity.Book;

import java.time.LocalDateTime;

public class RentMapper {

    // DTO -> 엔티티
    public static Rent from(
            RentCreateRequest request,
            User user,
            Group group,
            Book book
    ) {
        LocalDateTime now = LocalDateTime.now();
        return Rent.builder()
                .user(user)
                .group(group)
                .book(book)
                .rentalPeriod(request.rentalPeriod())
                .returnAt(request.returnAt())
                .rentalCount(request.rentalCount() != null ? request.rentalCount() : 0)
                .createdAt(request.createdAt() != null ? request.createdAt() : now)
                .updatedAt(request.updatedAt() != null ? request.updatedAt() : now)
                .build();
    }
}
