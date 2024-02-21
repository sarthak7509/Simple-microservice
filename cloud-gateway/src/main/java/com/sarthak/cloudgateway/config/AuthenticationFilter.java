package com.sarthak.cloudgateway.config;

import com.sarthak.cloudgateway.service.JWTservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JWTservice jwTservice;
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                // header contains token or not...
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing authorization header");
                }

                String authheaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authheaders!=null && authheaders.startsWith("Bearer ")){
                    authheaders = authheaders.substring(7);
                    try{
                        // Rest call to Auth Service
                        jwTservice.validateToken(authheaders);
                    }catch (Exception e){
                        System.out.println("Invalid Access");
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }

}
