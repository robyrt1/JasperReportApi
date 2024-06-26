package net.jasper.model;

import lombok.Data;

@Data
public class PacientService {
    private int nr_atendimento;

    public int getService(){
        return this.nr_atendimento;
    }
}
