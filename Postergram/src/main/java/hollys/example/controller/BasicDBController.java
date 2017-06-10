package hollys.example.controller;

import hollys.example.entity.test.Test;
import hollys.example.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
@RestController
@RequestMapping(value = "/api/db/test")
public class BasicDBController {
	private static final String REDIRECT_URL = "/api/db/test";

	@Autowired
	private TestRepository testRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Test> findEntity() {
		return testRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addNewEntity(HttpServletResponse response) throws IOException {
		Test newEntity = Test.create("youngho");
		testRepository.save(newEntity);

		response.sendRedirect(REDIRECT_URL);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateEntity(HttpServletResponse response) throws IOException {
		List<Test> foundEntities = testRepository.findAll();

		if (!foundEntities.isEmpty()) {
			Test firstEntity = foundEntities.get(0);
			testRepository.saveAndFlush(firstEntity);
		}

		response.sendRedirect(REDIRECT_URL);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteEntity(HttpServletResponse response) throws IOException {
		List<Test> foundEntities = testRepository.findAll();

		if (!foundEntities.isEmpty()) {
			Test firstEntity = foundEntities.get(0);

			testRepository.delete(firstEntity.getId());
		}

		response.sendRedirect(REDIRECT_URL);
	}
}
