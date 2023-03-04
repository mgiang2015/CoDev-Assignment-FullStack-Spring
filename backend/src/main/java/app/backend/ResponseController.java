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

@RestController
public class ResponseController {

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
}
