package Model;
public enum Situacao {
    ATIVO(1l,"ATIVO"),
    INATIVO(2l,"INATIVA");

    private long codigo;
    private String situacao;
    
    private Situacao(long codigo, String situacao) {
        this.codigo = codigo;
        this.situacao = situacao;
    }
}

