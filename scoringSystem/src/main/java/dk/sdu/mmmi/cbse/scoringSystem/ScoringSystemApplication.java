package dk.sdu.mmmi.cbse.scoringSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystemApplication{

	private int totalScore = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoringSystemApplication.class, args);
	}

	@GetMapping("/score")
	public int addPoint(@RequestParam(value = "point") int point) {
		totalScore += point;
		return totalScore ;
	}

	@GetMapping("/reset")
	public int resetScore() {
		totalScore = 0;
		return totalScore;
	}

}
