/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package site.jjilikeyou.www.controller;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import site.jjilikeyou.www.Controller.HTMLFilter;
import site.jjilikeyou.www.websocket.GetHttpSessionConfigurator;

@ServerEndpoint(value = "/websocket/chat/{username}",configurator=GetHttpSessionConfigurator.class)
public class ChatAnnotation {

    private static final Log log = LogFactory.getLog(ChatAnnotation.class);
    private static final String GUEST_PREFIX = "聊客";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatAnnotation> connections =
            new CopyOnWriteArraySet<ChatAnnotation>();

    private final String nickname;
    private Session session;

    public ChatAnnotation() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }


    @OnOpen
    public void start(@PathParam("username")String username,Session session,EndpointConfig config) {
    	//注释部分为获取httpsession的方法，需要创建一个类，先在用的直接从页面上传参数的方法
//    	HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//        User user = (User) httpSession.getAttribute("user");
//        String username = user.getUsername();

        this.session = session;
        connections.add(this);
        //String message = String.format("* %s %s", nickname, "has joined.");
        String message = String.format("* %s %s", username, "has joined.");
        broadcast(message);
    }


    @OnClose
    public void end(@PathParam("username")String username) {
        connections.remove(this);
        String message = String.format("* %s %s",
                username, "has disconnected.");
        broadcast(message);
    }


    @OnMessage
    public void incoming(String message,@PathParam("username")String username) {
        String filteredMessage = String.format("%s: %s",
                username, HTMLFilter.filter(message.toString()));
        broadcast(filteredMessage);
    }




    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }


    private static void broadcast(String msg) {
        for (ChatAnnotation client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }
}
