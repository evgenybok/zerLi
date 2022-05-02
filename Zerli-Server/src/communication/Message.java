package communication;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = -4880706272477371235L;
    private MessageType messageType;
    private MessageAnswer messageAnswer;
    private Object messageData;

    public Message(MessageType messageType, MessageAnswer messageAnswer, Object messageData) {
        super();
        this.messageType = messageType;
        this.messageData = messageData;
        this.messageAnswer = messageAnswer;
    }

    public Message(MessageType messageType, Object messageData) {
        super();
        this.messageType = messageType;
        this.messageData = messageData;
        this.messageAnswer = MessageAnswer.WAIT_RESPONSE;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Object getMessageData() {
        return messageData;
    }

    public MessageAnswer getMessageAnswer() {
        return messageAnswer;
    }

    public void setMessageAnswer(MessageAnswer messageAnswer) {
        this.messageAnswer = messageAnswer;
    }

    public void setMessageData(Object messageData) {
        this.messageData = messageData;
    }

    public String toString() {
        return "MESSAGE";
    }
}
