package gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

// tag::code[]
@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				//PRUEBA
				.route(p -> p
						.path("/get")
						.filters(f ->
								f.addRequestHeader("Hello", "World")
										.addRequestHeader("Example","Try"))
						.uri("http://httpbin.org:80"))
				//PROFILE
				.route(p->p
						.path("/MSProfile/**") //Nombre a donde enviar
						.filters(f -> f
								.rewritePath("/MSProfile/(?<segment>.*)", "/api/v1/${segment}")
								.filter((exchange, chain) -> {
									System.out.println("Request Path: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								}))
						.uri("http://hotech-profile:8001")) //Microservicio original
				//PAYMENT
				.route(p->p
						.path("/MSPayment/**") //Nombre a donde enviar
						.filters(f -> f
								.rewritePath("/MSPayment/(?<segment>.*)", "/api/v1/${segment}")
								.filter((exchange, chain) -> {
									System.out.println("Request Path: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								}))
						.uri("http://hotech-payment:8002")) //Microservicio original
				//MESSAGE
				.route(p->p
						.path("/MSMessage/**") //Nombre a donde enviar
						.filters(f -> f
								.rewritePath("/MSMessage/(?<segment>.*)", "/api/v1/${segment}")
								.filter((exchange, chain) -> {
									System.out.println("Request Path: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								}))
						.uri("http://hotech-message:8003")) //Microservicio original
				//INVENTORY
				.route(p->p
						.path("/MSInventory/**") //Nombre a donde enviar
						.filters(f -> f
								.rewritePath("/MSInventory/(?<segment>.*)", "/api/v1/${segment}")
								.filter((exchange, chain) -> {
									System.out.println("Request Path: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								}))
						.uri("http://hotech-inventory:8004")) //Microservicio original
				//ROOM
				.route(p->p
						.path("/MSRoom/**") //Nombre a donde enviar
						.filters(f -> f
								.rewritePath("/MSRoom/(?<segment>.*)", "/api/v1/${segment}")
								.filter((exchange, chain) -> {
									System.out.println("Request Path: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								}))
						.uri("http://hotech-room:8005")) //Microservicio original
				//REPORT
				.route(p->p
						.path("/MSReport/**") //Nombre a donde enviar
						.filters(f -> f
								.rewritePath("/MSReport/(?<segment>.*)", "/api/v1/${segment}")
								.filter((exchange, chain) -> {
									System.out.println("Request Path: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								}))
						.uri("http://hotech-report:8006")) //Microservicio original
				//TASK
				.route(p->p
						.path("/MSTask/**") //Nombre a donde enviar
						.filters(f -> f
								.rewritePath("/MSTask/(?<segment>.*)", "/api/v1/${segment}")
								.filter((exchange, chain) -> {
									System.out.println("Request Path: " + exchange.getRequest().getPath());
									return chain.filter(exchange);
								}))
						.uri("http://hotech-task:8007")) //Microservicio original
				.build();
	}
}