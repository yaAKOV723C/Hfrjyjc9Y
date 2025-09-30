// 代码生成时间: 2025-09-30 22:28:30
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

/**
 * KnowledgeRecommendationService is a Spring Boot component that provides functionality
 * to recommend knowledge points based on different criteria.
 */
@Service
public class KnowledgeRecommendationService {

    private final RestTemplate restTemplate;

    @Autowired
    public KnowledgeRecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves recommended knowledge points from a remote service.
     * @param criteria Criteria for knowledge point recommendation.
     * @return A list of recommended knowledge points.
     * @throws ResponseStatusException if the remote service returns an error status.
     */
    public List<String> recommendKnowledgePoints(@RequestParam String criteria) {
        try {
            ResponseEntity<List<String>> response = restTemplate.getForEntity(
                "http://knowledge-api.com/recommend?criteria={criteria}",
                List.class,
                criteria
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new ResponseStatusException(
                    response.getStatusCode(),
                    "Failed to retrieve knowledge points."
                );
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred while recommending knowledge points.");
        }
    }
}
