package com.natanalexandre.playlistmusicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/home")
public class PlaylistMusicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistMusicApiApplication.class, args);
	}

}
