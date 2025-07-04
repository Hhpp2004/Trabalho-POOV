package Model;

public class Doador {
    private long codigo;
    private String nome;
    private String cpf;
    private String contato;
    private Boolean tipoERhCorretos;
    private RH rh;
    private TipoSanguineo tipoSanguineo;
    private Situacao situacao;

    public Doador(long codigo, String nome, String cpf, String contato, Boolean tipoERhCorretos, RH rh,
            TipoSanguineo tipoSanguineo, Situacao situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf; 
        this.contato = contato;
        this.tipoERhCorretos = tipoERhCorretos;
        this.rh = rh;
        this.tipoSanguineo = tipoSanguineo;
        this.situacao = situacao;
    }

    public Doador(String nome, String cpf, String contato, RH rh, TipoSanguineo tipoSanguineo,
            Situacao situacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.tipoERhCorretos = false;
        this.rh = rh;
        this.tipoSanguineo = tipoSanguineo;
        this.situacao = situacao;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Doador: [codigo = ");
        builder.append(codigo);
        builder.append(", nome = ");
        builder.append(nome);
        builder.append(", cpf = ");
        builder.append(cpf);
        builder.append(", contato = ");
        builder.append(contato);
        builder.append(", tipo de RH corretos = ");
        builder.append(tipoERhCorretos);
        builder.append(", RH = ");
        builder.append(rh);
        builder.append(", tipo Sanguineo = ");
        builder.append(tipoSanguineo);
        builder.append(", situacao = ");
        builder.append(situacao);
        builder.append("]");
        return builder.toString();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (codigo ^ (codigo >>> 32));
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((contato == null) ? 0 : contato.hashCode());
        result = prime * result + ((tipoERhCorretos == null) ? 0 : tipoERhCorretos.hashCode());
        result = prime * result + ((rh == null) ? 0 : rh.hashCode());
        result = prime * result + ((tipoSanguineo == null) ? 0 : tipoSanguineo.hashCode());
        result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doador other = (Doador) obj;
        if (codigo != other.codigo)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (contato == null) {
            if (other.contato != null)
                return false;
        } else if (!contato.equals(other.contato))
            return false;
        if (tipoERhCorretos == null) {
            if (other.tipoERhCorretos != null)
                return false;
        } else if (!tipoERhCorretos.equals(other.tipoERhCorretos))
            return false;
        if (rh != other.rh)
            return false;
        if (tipoSanguineo != other.tipoSanguineo)
            return false;
        if (situacao != other.situacao)
            return false;
        return true;
    }
    public long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public Boolean getTipoERhCorretos() {
        return tipoERhCorretos;
    }
    public void setTipoERhCorretos(Boolean tipoERhCorretos) {
        this.tipoERhCorretos = tipoERhCorretos;
    }
    public RH getRh() {
        return rh;
    }
    public void setRh(RH rh) {
        this.rh = rh;
    }
    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    public Situacao getSituacao() {
        return situacao;
    }
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
