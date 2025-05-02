package shortlink.controller;

import org.springframework.beans.factory.annotation.Value;
import shortlink.exception.TooManyRequestsException;
import org.springframework.web.bind.annotation.*;
import shortlink.service.ShortenerService;
import java.util.concurrent.Semaphore;
import shortlink.dto.URLResponse;
import shortlink.dto.URLRequest;

@RestController
@RequestMapping("/api")
public class ShortenerController {
    private final ShortenerService shortenerService;
    private final Semaphore semaphore;

    public ShortenerController(ShortenerService shortenerService, 
                             @Value("${rate.limit.max-concurrent:10}") int maxConcurrentRequests) {
        this.shortenerService = shortenerService;
        this.semaphore = new Semaphore(maxConcurrentRequests);
    }

    @PostMapping("/encode")
    public URLResponse encode(@RequestBody URLRequest request) throws TooManyRequestsException {
        if (!semaphore.tryAcquire()) {
            throw new TooManyRequestsException("Too many concurrent requests");
        }
        try {
            return new URLResponse(shortenerService.shorten(request.getUrl()));
        } finally {
            semaphore.release();
        }
    }

    @PostMapping("/decode")
    public URLResponse decode(@RequestBody URLRequest request) throws TooManyRequestsException {
        if (!semaphore.tryAcquire()) {
            throw new TooManyRequestsException("Too many concurrent requests");
        }
        try {
            return new URLResponse(shortenerService.expand(request.getUrl()));
        } finally {
            semaphore.release();
        }
    }
}