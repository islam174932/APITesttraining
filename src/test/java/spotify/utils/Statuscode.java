package spotify.utils;



public enum Statuscode {
    CODE_200(200, "HTTP/1.1 200 OK"),
    CODE_400(400, "HTTP/1.1 400 Bad Request"),
    CODE_201(201, "HTTP/1.1 201 Created");

    public final int code;
    public final String message;

    Statuscode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

