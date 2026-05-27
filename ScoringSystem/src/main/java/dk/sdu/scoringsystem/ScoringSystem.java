package dk.sdu.scoringsystem;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystem {

    private Long score = 0L;

    @GetMapping("/score")
    public Long addScore(@RequestParam(value = "point") Long point) {
        score += point;
        return score;
    }

    @GetMapping("/score/current")
    public Long getScore() {
        return score;
    }
}