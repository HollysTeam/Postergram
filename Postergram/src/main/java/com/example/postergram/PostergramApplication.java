package com.example.postergram;

import com.example.postergram.entity.poster.Poster;
import com.example.postergram.entity.poster.PosterType;
import com.example.postergram.entity.poster.PosterTypeRel;
import com.example.postergram.entity.user.User;
import com.example.postergram.repository.poster.PosterRepository;
import com.example.postergram.repository.poster.PosterTypeRelRepository;
import com.example.postergram.repository.poster.PosterTypeRepository;
import com.example.postergram.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class PostergramApplication implements CommandLineRunner {

	@Autowired
	private PosterTypeRepository posterTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PosterRepository posterRepository;

	@Autowired
	private PosterTypeRelRepository posterTypeRelRepository;

	public static void main(String[] args) {
		SpringApplication.run(PostergramApplication.class, args);
	}

	@Override
	@Transactional(readOnly = true)
	public void run(String... arg0) throws Exception {
		Poster newPoster = addTestEntities();
		Poster existPoster = posterRepository.findOne(8);

		log.info("{}", existPoster.getContent());
		log.info("{}", existPoster.getPosterTypeRels().get(0).getPosterType().getTypeName());
		log.info("{}", newPoster.getContent());
	}

	@Transactional
	public Poster addTestEntities() {
		PosterType posterType = PosterType.create("type");
		posterTypeRepository.save(posterType);

		User newUser = User.create("chk@postergram.com", "wj", false);
		userRepository.save(newUser);

		Poster newPoster = Poster.create(newUser, "냠냠 먹자", false);
		posterRepository.save(newPoster);

		PosterTypeRel posterTypeRel = PosterTypeRel.create(newPoster, posterType, false);
		posterTypeRelRepository.save(posterTypeRel);
		return newPoster;
	}
}
