package PauloAugustoMartins.PetShop;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.YearMonth;
import java.util.Date;

public class Cachorro extends Animal{
    boolean adult;
    long meses;

    public Cachorro(int codigoAnimal, Cliente ClienteDono, Date dataNascimento) {
        super(codigoAnimal, ClienteDono, dataNascimento);
        LocalDate localdate = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(YearMonth.from(localdate),YearMonth.from(now));
        if(months > 14){
            adult = true;
            meses = months;
        }else{
            adult = false;
        }
    }

    public boolean isAdult(){
        return adult;
    }

    public long getMeses(){
        return meses;
    }
}
