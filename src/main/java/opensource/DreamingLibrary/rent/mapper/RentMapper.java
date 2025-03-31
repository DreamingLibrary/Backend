package opensource.DreamingLibrary.rent.mapper;

import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.rent.dto.request.RentCreateRequest;
import opensource.DreamingLibrary.rent.entity.Rent;
import opensource.DreamingLibrary.user.entity.User;

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
