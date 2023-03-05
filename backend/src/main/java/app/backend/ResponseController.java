package app.backend;

import app.backend.error.EmptyResponseException;
import app.backend.error.InvalidParameterException;
import app.backend.response.RandomCountryResponse;
import app.backend.response.Top3BookResponse;
import app.backend.response.ResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static app.backend.response.RandomCountryResponse.*;

@RestController
public class ResponseController {
    private static final Random rand = new Random();
    private static final Long[] allCodes = new Long[]{CODE_SG, RandomCountryResponse.CODE_MY, RandomCountryResponse.CODE_US};

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResponseRepository responseRepository;

    @CrossOrigin
    @GetMapping("/getTop3ReadBooks")
    public List<Top3BookResponse> getTop3ReadBooks(@RequestParam(name = "country_code", required = true) String code) throws InvalidParameterException, EmptyResponseException {
        try {
            Long country_id = Long.parseLong(code);
        } catch (Exception e) {
            throw new InvalidParameterException();
        }

        Long country_id = Long.parseLong(code);
        if (!country_id.equals(CODE_SG) && !country_id.equals(CODE_MY) && !country_id.equals(CODE_US)) {
            throw new InvalidParameterException();
        }

        List<Top3BookResponse> res = responseRepository.getTop3ReadBooks(country_id);
        if (res.size() == 0) {
            logger.error("Cannot find Top 3 Read Books");
            throw new EmptyResponseException();
        }

        return res;
    }

    @CrossOrigin
    @GetMapping("/getRandomCountry")
    public RandomCountryResponse getRandomCountry() {
        int rnd = rand.nextInt(allCodes.length);
        return RandomCountryResponse.fromLong(allCodes[rnd]);
    }
}

