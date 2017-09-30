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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

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
}
