package org.myProject.focus_flow_auth_service_api.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    Map<String, Integer> requestCounts = new ConcurrentHashMap<>();

    static int MAX_REQUESTS_PER_MINUTE = 50;

    static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public RateLimitingFilter() {
        scheduler.scheduleAtFixedRate(requestCounts::clear, 1, 1, TimeUnit.MINUTES);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String clientIp = request.getRemoteAddr();

        requestCounts.putIfAbsent(clientIp, 0);
        int requestCount = requestCounts.get(clientIp);

        if (requestCount >= MAX_REQUESTS_PER_MINUTE) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests - please try again later.");
            return;
        }

        requestCounts.computeIfPresent(clientIp, (key, count) -> count + 1);
        filterChain.doFilter(request, response);
    }
}
