package com.fw.todoapp.web.api;

import com.fw.todoapp.entities.Tag;
import com.fw.todoapp.repositories.TagRepository;
import com.fw.todoapp.web.requests.CreateTagRequest;
import com.fw.todoapp.web.responses.Error;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {

  private final TagRepository tagRepository;

  public TagController(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @PostMapping("/create")
  public ResponseEntity<?> createTag(@RequestBody @Valid CreateTagRequest request) {
    if(tagRepository.findOneByName(request.getName()).isPresent()) {
      return ResponseEntity.badRequest().body(new Error("Tag with name '" + request.getName() + "' already exists"));
    }
    Tag tag = new Tag();
    tag.setName(request.getName());
    tagRepository.save(tag);
    return ResponseEntity.ok("OK");
  }

}
