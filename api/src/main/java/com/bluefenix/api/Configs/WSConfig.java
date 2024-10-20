package com.bluefenix.api.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Configuração base do WebSocket. Tá ligado né fi? Sem isso aqui não funciona. 

@Configuration
@EnableWebSocketMessageBroker // Vamos precisar do uso de STOMP. Como a utilização do WebSocket envolverá vários clientes, será necessário o MessageBroker para que possamos prosseguir com um desenvolvimento complexo
public class WSConfig implements WebSocketMessageBrokerConfigurer {
    
    // Registra as URLs permitidas. Enquanto esse projeto for somente para fins educacionais, liberamos o acesso por completo mesmo. Mas PELO AMOR DE DEUS, se isso aqui deixar de ser somente para uso educacional, não esquece de limitar esse CORS, por favor irmão
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
        .setAllowedOrigins("http://127.0.0.1:5500")
        .withSockJS();
    }

    // Define os endpoints. Servidor para cliente, cliente para servidor
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/filaWS");
        config.setApplicationDestinationPrefixes("/app");
    }
}