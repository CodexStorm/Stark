package org.kurukshetra.stark.Entities;

/**
 * Created by ompra on 11/2/2017.
 */

public class ChatMessage {
    private boolean  isMine;
    private String content;

    public ChatMessage( String content,boolean isMine) {
        this.isMine = isMine;
        this.content = content;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
