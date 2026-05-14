package dk.sdu.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystem {

    private Long Score = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystem.class, args);
    }

    @GetMapping("/score")
    public Long addScore(@RequestParam(value = "point") Long point) {
        Score += point;
        return Score;
    }

    @GetMapping("/score/current")
    public Long getScore() {
        return Score;
    }
}