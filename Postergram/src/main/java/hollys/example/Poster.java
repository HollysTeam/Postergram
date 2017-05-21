package hollys.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Poster {

	private @Id @GeneratedValue Long id;
	

	private Poster() {}

}