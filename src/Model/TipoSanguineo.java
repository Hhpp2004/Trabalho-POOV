package Model;
public enum TipoSanguineo {
    A(1l,"A"),
    B(2l,"B"),
    AB(3l,"AB"),
    O(4l,"O"),
    DESCONHECIDO(5l,"DESCONHECIDO");

    private long codigo;
    private String tipo;
    
    private TipoSanguineo(long codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }
}

