package pt.amane.wsmongodb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.amane.wsmongodb.models.dtos.PostDTO;
import pt.amane.wsmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id){
		PostDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<PostDTO>> findAll(@RequestParam(value = "text", defaultValue = "") String text){
		List<PostDTO> list = service.findAll(text);
		return ResponseEntity.ok().body(list);
	}

}
