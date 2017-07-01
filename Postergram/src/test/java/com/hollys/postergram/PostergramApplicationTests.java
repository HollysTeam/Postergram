package com.hollys.postergram;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hollys.postergram.entity.poster.Poster;
import com.hollys.postergram.entity.poster.PosterImage;
import com.hollys.postergram.entity.poster.PosterType;
import com.hollys.postergram.entity.poster.PosterTypeRel;
import com.hollys.postergram.entity.user.User;
import com.hollys.postergram.repository.poster.PosterImageRepository;
import com.hollys.postergram.repository.poster.PosterRepository;
import com.hollys.postergram.repository.poster.PosterTypeRelRepository;
import com.hollys.postergram.repository.poster.PosterTypeRepository;
import com.hollys.postergram.repository.user.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PostergramApplicationTests {
	@Autowired
	private PosterTypeRepository posterTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PosterRepository posterRepository;

	@Autowired
	private PosterImageRepository posterImageRepository;

	@Autowired
	private PosterTypeRelRepository posterTypeRelRepository;

	@Test
	@Transactional(readOnly = true)
	public void createPosterTest() {
		Poster newPoster = addTestEntities();
		Poster existPoster = posterRepository.findOne(1);
		
		// log.info("{}", existPoster.getContent());
		// log.info("{}",
		// existPoster.getPosterTypeRels().get(0).getPosterType().getTypeName());
		// log.info("{}", newPoster.getContent());

		assertEquals(newPoster.getId(), existPoster.getId());
		//assertEquals(existPoster.getPosterTypeRels().get(0).getPosterType().getTypeName(),newPoster.getPosterTypeRels().get(0).getPosterType());
		assertEquals(existPoster.getPosterImages().get(0).getImageURL(),"http://images.postergram.com/1");
	}

	@Transactional
	public Poster addTestEntities() {
		PosterType posterType = PosterType.create("type");
		posterTypeRepository.save(posterType);

		User newUser = User.create("chk@postergram.com", "wj", false);
		userRepository.save(newUser);

		PosterImage posterImage = PosterImage.create("http://images.postergram.com/1", false);
		posterImageRepository.save(posterImage);

		List<PosterImage> posterImages = new ArrayList<PosterImage>();
		posterImages.add(posterImage);
		
		Poster newPoster = Poster.create(newUser, posterImages, "냠냠 먹자", false);
		posterRepository.save(newPoster);

		PosterTypeRel posterTypeRel = PosterTypeRel.create(newPoster, posterType, false);
		posterTypeRelRepository.save(posterTypeRel);
		
		return newPoster;
	}

}
