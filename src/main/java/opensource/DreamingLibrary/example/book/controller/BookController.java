package opensource.DreamingLibrary.example.book.controller;

import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.example.book.dto.request.BookCreateRequest;
import opensource.DreamingLibrary.example.book.dto.response.BookResponse;
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
    public SuccessResponse<SingleResult<BookResponse>> createBook(@RequestBody BookCreateRequest request) {
        BookResponse createdBookResponse = bookService.createBook(request);

        SingleResult<BookResponse> singleResult = new SingleResult<>();
        singleResult.setData(createdBookResponse);

        return SuccessResponse.ok(singleResult);
    }

    /**
     * READ (단일 조회)
     * GET /api/books/{bookId}
     */
    @GetMapping("/{bookId}")
    public SuccessResponse<SingleResult<BookResponse>> getBook(@PathVariable Long bookId) {
        BookResponse bookResponse = bookService.getBook(bookId);

        SingleResult<BookResponse> singleResult = new SingleResult<>();
        singleResult.setData(bookResponse);

        return SuccessResponse.ok(singleResult);
    }
}
