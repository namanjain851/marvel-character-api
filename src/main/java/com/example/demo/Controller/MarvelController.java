package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.data.MarvelCharacterWrapper;
import com.example.demo.service.MarvelService;


@RestController
public class MarvelController {
	
	@Autowired
    private MarvelService marvelService;
	
	@RequestMapping(value = "api/{nameStartsWith}" , method = RequestMethod.GET)
	public ResponseEntity<MarvelCharacterWrapper> getOutput(@PathVariable String nameStartsWith,@RequestParam(name = "page") String pageNo){
		MarvelCharacterWrapper output = marvelService.getMarvelCharacter(nameStartsWith,pageNo);
		return new ResponseEntity<MarvelCharacterWrapper>(output,HttpStatus.ACCEPTED);
	}
}
