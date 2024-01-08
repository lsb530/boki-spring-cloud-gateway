package boki.tistory.springcloudgatewayjava.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRoute {
    @Bean
    public RouteLocator userRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // users
                .route("users_route_1", r -> r.path("/users")
                        .and().weight("users", 7)
                        .filters(f -> f.rewritePath("/users", "/rest/v1/api/users"))
                        .uri("http://localhost:4004"))
                .route("users_route_2", r -> r.path("/users")
                        .and().weight("users", 3)
                        .filters(f -> f.rewritePath("/users", "/rest/v1/api/users"))
                        .uri("http://localhost:4005"))
                .build();
    }
}
