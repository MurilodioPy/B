package BolaoMega;

/**
 *
 * @author Murilo
 */
public enum Tabela{
    SEIS(5.00),
    SETE(35.50),
    OITO(140.00),
    NOVE(420.00),
    DEZ(1050.00),
    ONZE(2310.00),
    DOZE(4620.00),
    TREZE(8580.00),
    QUATORZE(15015.00),
    QUINZE(22025.00),
    DEZESSEIS(40040.00),
    DEZESSETE(61880.00),
    DEZOITO(92820.00),
    DEZENOVE(135660.00),
    VINTE(193800.00);

    private double valor;

    Tabela(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

}
