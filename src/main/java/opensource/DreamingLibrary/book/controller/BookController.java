package opensource.DreamingLibrary.book.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import opensource.DreamingLibrary.book.dto.request.BookCreateRequest;
import opensource.DreamingLibrary.book.dto.response.BookResponse;
import opensource.DreamingLibrary.book.entity.Category;
import opensource.DreamingLibrary.book.service.BookService;
import opensource.DreamingLibrary.global.dto.response.SuccessResponse;
import opensource.DreamingLibrary.global.dto.response.result.ListResult;
import opensource.DreamingLibrary.global.dto.response.result.SingleResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "책(Book)")
public class BookController {

    private final BookService bookService;

    /**
     * 책 등록
     */
    @PostMapping
    @Operation(summary = "책 등록")
    public SuccessResponse<SingleResult<Long>> createBook(@Valid @RequestBody BookCreateRequest request) {
        SingleResult<Long> result = bookService.createBook(request);
        return SuccessResponse.ok(result);
    }

    /**
     * READ (단일 조회)
     * GET /api/books/{bookId}
     */
    @GetMapping("/{bookId}")
    @Operation(summary = "책 단건 조회")
    public SuccessResponse<SingleResult<BookResponse>> getBookById(@PathVariable("bookId") Long bookId) {
        SingleResult<BookResponse> result = bookService.getBookById(bookId);
        return SuccessResponse.ok(result);
    }

    /**
     * 책 목록 조회
     */
    @GetMapping
    @Operation(summary = "책 전체 목록 조회")
    public SuccessResponse<ListResult<BookResponse>> getAllBooks(){
        ListResult<BookResponse> result = bookService.getAllBooks();
        return SuccessResponse.ok(result);
    }

    /**
     * 카테고리별 조회
     */
    @GetMapping("/category")
    @Operation(summary = "카테고리별 책 조회")
    public SuccessResponse<ListResult<BookResponse>> getBooksByCategory(@RequestParam("category")Category category){
        ListResult<BookResponse> result = bookService.getBooksByCategory(category);
        return SuccessResponse.ok(result);
    }


    /**
     * 제목 또는 저자명으로 도서 검색
     */
    @GetMapping("/search")
    @Operation(summary = "제목 또는 저자명으로 책 검색")
    public SuccessResponse<ListResult<BookResponse>> searchBooks(@RequestParam("keyword") String keyword){
        ListResult<BookResponse> result = bookService.searchBooksByKeyword(keyword);
        return SuccessResponse.ok(result);
    }
}
