package co.edu.usbbog.moneybox.helperclasses;

public class ListEmelemnt {

    public String color;
    public String name;
    public String concepto;
    public String valor;
    public String fecha;
    public String billT;


    public ListEmelemnt(String color, String name, String concepto, String valor, String fecha, String billT) {
        this.color = color;
        this.name = name;
        this.concepto = concepto;
        this.valor = valor;
        this.fecha = fecha;
        this.billT = billT;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getBillT() {
        return billT;
    }

    public void setBillT(String billT) {
        this.billT = billT;
    }

    public ListEmelemnt(String dato) {
    }
}
