package Model;

public enum RH {
    POSITIVO(1l, "POSITIVO"),
    NEGATIVO(2l, "NEGATIVO"),
    DESCONHECIDO(3l, "DESCONHECIDO");

    private long codigo;
    private String rh;

    private RH(long codigo, String rh) {
        this.codigo = codigo;
        this.rh = rh;
    }
}
