package com.hollys.postergram;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.hollys.postergram.entity.poster.Poster;
import com.hollys.postergram.entity.poster.type.PosterType;
import com.hollys.postergram.entity.poster.type.PosterTypeRel;
import com.hollys.postergram.entity.user.User;
import com.hollys.postergram.repository.poster.PosterRepository;
import com.hollys.postergram.repository.poster.type.PosterTypeRelRepository;
import com.hollys.postergram.repository.poster.type.PosterTypeRepository;
import com.hollys.postergram.repository.user.UserRepository;

@EnableJpaAuditing
@SpringBootApplication
public class PostergramApplication implements CommandLineRunner {

	@Autowired
	PosterTypeRepository posterTypeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PosterRepository posterRepository;
	@Autowired
	PosterTypeRelRepository posterTypeRelRepository;

	public static void main(String[] args) {
		SpringApplication.run(PostergramApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Poster newPoster = addTestEntitys();
		
		Poster existPoster = posterRepository.findAll().get(0);
		
		// test 5
		System.out.println(existPoster.getContent());
		System.out.println(existPoster.getPosterTypeRel().get(0).getPosterType().getTypeName());
		
		// test 6
		System.out.println(newPoster.getContent());
		System.out.println(newPoster.getPosterTypeRel().get(0).getPosterType().getTypeName());

	}

	@Transactional
	public Poster addTestEntitys() {
		// test 1
		PosterType newPosterType = PosterType.create("분식");
		posterTypeRepository.save(newPosterType);

		// test 2
		User newUser = User.create("chk@postergram.com", "wj", false);
		userRepository.save(newUser);

		// test 3
		Poster newPoster = Poster.create(newUser, "냠냠먹자", false);
		posterRepository.save(newPoster);

		// test 4
		PosterTypeRel posterTypeRel1 = PosterTypeRel.create(newPoster, newPosterType, false);
		posterTypeRelRepository.save(posterTypeRel1);
		
		return newPoster;
	}
}
