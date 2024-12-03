public class Despesas {

    private double valor;
    private String data;
    private String desc;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        desc = desc;
    }

    Despesas(double valor, String data, String desc){
        this.valor = valor;
        this.data = data;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Despesas" + '\'' +
                "valor= R$" + valor +
                ", data='" + data + '\'' +
                ", descrição='" + desc + '\'';
    }
}
