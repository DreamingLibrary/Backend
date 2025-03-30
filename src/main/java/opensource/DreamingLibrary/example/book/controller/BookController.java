package opensource.DreamingLibrary.example.book.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.book.dto.request.BookCreateRequest;
import opensource.DreamingLibrary.example.book.entity.Book;
import opensource.DreamingLibrary.example.book.service.BookService;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * CREATE
     * POST /api/books
     */
    @PostMapping
    public SuccessResponse<SingleResult<Book>> createBook(@RequestBody BookCreateRequest request) {
        Book createdBook = bookService.createBook(request);

        // SingleResult에 Book 데이터를 담음
        SingleResult<Book> singleResult = new SingleResult<>();
        singleResult.setData(createdBook);

        // SuccessResponse로 감싸 최종 응답
        return SuccessResponse.ok(singleResult);
    }

    /**
     * READ (단일 조회)
     * GET /api/books/{bookId}
     */
    @GetMapping("/{bookId}")
    public SuccessResponse<SingleResult<Book>> getBook(@PathVariable Long bookId) {
        Book book = bookService.getBook(bookId);

        SingleResult<Book> singleResult = new SingleResult<>();
        singleResult.setData(book);

        return SuccessResponse.ok(singleResult);
    }


}
