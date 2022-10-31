package PauloAugustoMartins.PetShop;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public abstract class Animal {
    int cod;
    Cliente dono;
    Date nascimento;
    long meses;
    boolean adult = false;
    
    public Animal(int codigoAnimal, Cliente ClienteDono, Date dataNascimento) {
        cod = codigoAnimal;
        dono = ClienteDono;
        nascimento = dataNascimento;
        LocalDate localdate = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(YearMonth.from(localdate),YearMonth.from(now));
        meses = months;
        if (Animal.class.equals(Gato.class)){
            if(meses > 12){
                adult = true;
            }
        };
    }

    public boolean verificarAdulto(){
        return adult;
    }

    public int getCod() {
        return cod;
    }

    public Cliente getDono() {
        return dono;
    }

    public Date getNascimento(){
        return nascimento;
    }

    public long getMeses(){
        return meses;
    }
}
