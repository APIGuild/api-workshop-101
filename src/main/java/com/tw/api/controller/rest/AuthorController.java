package com.tw.api.controller.rest;

import com.tw.api.contract.AuthorRequest;
import com.tw.api.contract.AuthorResponse;
import com.tw.api.model.Author;
import com.tw.api.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/${api.version}")
@Api(value = "Author REST API", description = "Author related API")
public class AuthorController {

    private AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @ApiOperation(value = "POST", notes = "Create author")
    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest) {
        AuthorResponse authorResponse = service.createAuthor(authorRequest);
        return ResponseEntity.status(CREATED).body(authorResponse);
    }

    @ApiOperation(value = "GET", notes = "Find author with author id")
    @RequestMapping(value = "/author/{authorId}", method = RequestMethod.GET)
    public ResponseEntity<AuthorResponse> findAuthorWithId(@PathVariable String authorId) {
        AuthorResponse authorResponse = service.findAuthor(authorId);
        HttpStatus status = authorResponse == null ? INTERNAL_SERVER_ERROR : OK;
        return ResponseEntity.status(status).body(authorResponse);
    }

    @ApiOperation(value = "GET", notes = "Find all authors")
    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<List<AuthorResponse>> findAuthors() {
        List<AuthorResponse> authorResponses = service.findAllAuthor();
        return ResponseEntity.status(OK).body(authorResponses);
    }

    @ApiOperation(value = "PUT", notes = "Update author")
    @RequestMapping(value = "/author/{authorId}", method = RequestMethod.PUT)
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable String authorId,
                                                       @RequestBody AuthorRequest authorRequest) {
        AuthorResponse authorResponse = service.updateAuthor(authorId, authorRequest);
        return ResponseEntity.status(OK).body(authorResponse);
    }

    @ApiOperation(value = "DELETE", notes = "Delete author")
    @RequestMapping(value = "/author/{authorId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> updateAuthor(@PathVariable String authorId) {
        service.deleteAuthor(authorId);
        return ResponseEntity.status(OK).body("success");
    }
}
