package co.edu.usbbog.moneybox.helperclasses;

public class ConnAdapter {




    public String url(String site){
        final String IP = "http://172.17.1.158:3000/";
        final String baseUrl = IP+site;
        return baseUrl;
    }

    public String ip(){
        final String IP = "http://172.17.1.158:3000/";
        return IP;
    }

}
