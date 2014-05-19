/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Eusuph
 */
@ServerEndpoint("/websocket")
/*
 * This annotation makes the class to be WebSocket endpoint
 * The value within cotation marks specifies the URL where 
 * the endpoint is published
 */
public class ChatServer {

    private static final Set<Session> peers
            = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    /*
     *used when WebSocket opens the method
     */
    public void onOpen(Session peer) {
        peers.add(peer);
    }

    @OnClose
    /*
     *used when WebSocket closes the method
     */
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void message(String message, Session client) throws
            IOException, EncodeException {
        for (Session session : peers) {
            session.getBasicRemote().sendObject(message);

        }
    }

}
