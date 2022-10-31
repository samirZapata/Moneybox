package co.edu.usbbog.moneybox.helperclasses;

public class ConnAdapter {




    public String url(String site){
        final String IP = "http://192.168.0.3:3300/";
        final String baseUrl = IP+site;
        return baseUrl;
    }

    public String ip(){
        final String IP = "http://192.168.0.3:3300/";
        return IP;
    }

}
