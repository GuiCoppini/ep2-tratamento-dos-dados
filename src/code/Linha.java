package code;

// Classe usada soh pra guardar os valores
public class Linha {
    String data;
    double open;
    double high;
    double low;
    double close;
    double closeAdj;
    double volume;

    public Linha(String data, double open, double high, double low, double close, double closeAdj, double volume) {
        this.data = data;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.closeAdj = closeAdj;
        this.volume = volume;
    }
//    Date;Open;High;Low;Close;Adj Close;Volume
}
