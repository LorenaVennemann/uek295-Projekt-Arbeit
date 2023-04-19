package ch.noseryoung.uek295.domain.entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogEntryController {

  private final BlogEntryService blogEntryService;

  public BlogEntryController(BlogEntryService blogEntryService) {
    this.blogEntryService = blogEntryService;
  }

  @GetMapping("/")
  @Operation(summary = "Fetches all Entry in the database", description = "Fetches all Entry and returns JSON with status code 200 when succesed")
  @PreAuthorize("hasAuthority('READ')")
  public ResponseEntity<List<BlogEntry>> getAllBlogEntries() {
    List<BlogEntry> blogEntries = blogEntryService.getAllBlogEntries();
    return ResponseEntity.ok(blogEntries);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Fetches a Entry in the database with a given id", description = "Fetches all Entry and returns JSON with status code 200 when succesed")
  @PreAuthorize("hasAuthority('READ')")
  public ResponseEntity<BlogEntry> getBlogEntryById(@PathVariable Long id) {
    BlogEntry blogEntry = blogEntryService.getBlogEntryById(id);
    return ResponseEntity.ok(blogEntry);
  }

  @PostMapping
  @Operation(summary = "Creates a entry and saves it into the database", description = "Fetches all Entry and returns JSON with status code 200 when succesed")
  @PreAuthorize("hasAuthority('CREATE')")
  public ResponseEntity<Void> addBlogEntry(@Valid @RequestBody BlogEntry blogEntry) {
    blogEntryService.addBlogEntry(blogEntry);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{id}")
  @Operation(summary = "Updates an Entry in the Database with a given id", description = "Fetches all Entry and returns JSON with status code 200 when succesed")
  @PreAuthorize("hasAuthority('UPDATE')")
  public ResponseEntity<Void> updateBlogEntry(@PathVariable Long id, @Valid @RequestBody BlogEntry updatedBlogEntry) {
    blogEntryService.updateBlogEntry(id, updatedBlogEntry);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletes a given entry with a given id", description = "Fetches all Entry and returns JSON with status code 200 when succesed")
  @PreAuthorize("hasAuthority('DELETE')")
  public ResponseEntity<Void> deleteBlogEntry(@PathVariable Long id) {
    blogEntryService.deleteBlogEntry(id);
    return ResponseEntity.ok().build();
  }
}