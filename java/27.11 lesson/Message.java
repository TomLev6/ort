public class Message {
    //the msg,
    String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return " Message{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
