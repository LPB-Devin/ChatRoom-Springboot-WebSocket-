package com.chat.springbootwebsocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker       //启动WebSockt服务器
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册一个webSocket端点，客户端使用它连接WebSocket服务器
     * - withSockJS()：用于不支持webSocket的浏览器启用后备选项，使用了sockJS
     * - STOMP代表简单文本导向的消息传递协议，用于定义数据交换的格式和规则；因为WebSocket只
     *      一种通信协议，没有定义以下内容：
     *          = 如何仅向订阅主题的用户发送消息；
     *          = 如何向特定用户发送消息等等。
     *
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * 配置消息代理，将消息从一个客户端路由到另一个客户端。
     *  - 第一行定义了以“/app”开头的消息应该路由到消息处理方法
     *  - 第二行定义了以“/topic”开头的消息应该路由到消息代理。消息代理再向订阅特定主题的所有
     *      连接客户端广播消息。
     *
     *      使用的是内存中的消息代理，之后可以使用RabbitMQ或者ActiveMQ等其他的消息代理。
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
