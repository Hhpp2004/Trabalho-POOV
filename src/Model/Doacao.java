package Model;

import java.time.LocalDate;
import java.time.LocalTime;


public class Doacao {
    private long codigo;
    private LocalDate data;
    private LocalTime hora;
    private double volume;
    private Doador doador;

    public Doacao(long codigo, LocalDate data, LocalTime hora, double volume, Doador doador) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
        this.volume = volume;
        this.doador = doador;
    }

    public Doacao(LocalDate data, LocalTime hora, double volume, Doador doador) {
        this.data = data;
        this.hora = hora;
        this.volume = volume;
        this.doador = doador;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (codigo ^ (codigo >>> 32));
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        long temp;
        temp = Double.doubleToLongBits(volume);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((doador == null) ? 0 : doador.hashCode());
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
        Doacao other = (Doacao) obj;
        if (codigo != other.codigo)
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume))
            return false;      
        if (doador == null) {
            if (other.doador != null)
                return false;
        } else if (!doador.equals(other.doador))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Doacao: [codigo = ");
        builder.append(codigo);
        builder.append(", data = ");
        builder.append(data);
        builder.append(", hora = ");
        builder.append(hora);
        builder.append(", volume = ");
        builder.append(volume+" ml");
        builder.append("\n");
        builder.append("Doador: = ");
        builder.append(doador);
        builder.append("]");
        return builder.toString();
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }  

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

}
