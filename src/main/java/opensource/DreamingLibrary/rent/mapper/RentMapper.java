package opensource.DreamingLibrary.rent.mapper;

import opensource.DreamingLibrary.rent.dto.request.RentCreateRequest;
import opensource.DreamingLibrary.rent.entity.Rent;
import opensource.DreamingLibrary.user.entity.User;
import opensource.DreamingLibrary.book.entity.Book;


public class RentMapper {

    public static Rent from(
            RentCreateRequest request,
            User user,
            Book book
    ) {
        return Rent.builder()
                .user(user)
                .book(book)
                .rentalPeriod(14)    // 추후 숫자 static 변수로 수정 예정
                .rentalCount(0)      // 추후 숫자  static 변수로 수정 예정
                .returnAt(null)      // service에서 값을 넣을 예정인데 추후 수정해야할 코드일지 다시 볼 예정..
                .build();
    }
}
