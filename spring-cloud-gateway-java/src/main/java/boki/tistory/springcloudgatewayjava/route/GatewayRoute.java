package boki.tistory.springcloudgatewayjava.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoute {
    @Bean
    public RouteLocator userRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // users
                .route("users_route", r -> r.path("/users")
                        .filters(f -> f.rewritePath("/users", "/rest/v1/api/users"))
                        .uri("http://localhost:4004"))
                .build();
    }
}
