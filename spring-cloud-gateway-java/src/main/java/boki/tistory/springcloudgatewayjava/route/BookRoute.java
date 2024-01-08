package boki.tistory.springcloudgatewayjava.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookRoute {
    @Bean
    public RouteLocator bookRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // books
                .route("books_route", r -> r.path("/books")
                        .filters(
                                f -> f.rewritePath("/books", "/rest/v2/api/books")
                        )
                        .uri("http://localhost:5004"))
                .build();
    }
}
