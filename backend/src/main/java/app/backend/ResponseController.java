package app.backend;

import app.backend.response.Response;
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

@RestController
public class ResponseController {
    private static final Random rand = new Random();
    private static final Long[] allCodes = new Long[]{Country.CODE_SG, Country.CODE_MY, Country.CODE_US};

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResponseRepository responseRepository;

    @CrossOrigin
    @GetMapping("/getTop3ReadBooks")
    public List<Response> getTop3ReadBooks(@RequestParam(name = "_code", required = true) String code) {
        try {
            Long country_id = Long.parseLong(code);
            List<Response> res = responseRepository.getTop3ReadBooks(country_id);
            if (res.size() == 0) {
                logger.error("Cannot find Top 3 Read Books");
                throw new Exception("Cannot find Top 3 Read Books");
            }

            return res;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    @CrossOrigin
    @GetMapping("/getRandomCountry")
    public CountryResponse getRandomCountry() {
        int rnd = rand.nextInt(allCodes.length);
        Country c = Country.fromLong(allCodes[rnd]);
        logger.info("Country -> {}", c);
        CountryResponse resp = new CountryResponse(c);
        logger.info("Response -> {}", resp);
        return resp;
    }
}

class CountryResponse {
    Country country;

    public CountryResponse(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}

class Country {
    public static final Long CODE_SG = 0L;
    public static final Long CODE_MY = 1L;
    public static final Long CODE_US = 2L;

    private final String full_name;
    private final String country_code;

    public Country(String full_name, String country_code) {
        this.full_name = full_name;
        this.country_code = country_code;
    }

    public static Country fromLong(Long id) {
        if (id.equals(CODE_SG)) {
            String full_name = "SG";
            String country_code = "0" + id;
            return new Country(full_name, country_code);
        } else if (id.equals(CODE_MY)) {
            String full_name = "MY";
            String country_code = "0" + id;
            return new Country(full_name, country_code);
        } else if (id.equals(CODE_US)) {
            String full_name = "US";
            String country_code = "0" + id;
            return new Country(full_name, country_code);
        }

        return new Country(null, null);
    }

    public String getFull_name() {
        return full_name;
    }

    public String getCountry_code() {
        return country_code;
    }
}
