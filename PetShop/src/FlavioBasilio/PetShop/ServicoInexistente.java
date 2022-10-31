package PauloAugustoMartins.PetShop;

public class ServicoInexistente extends Exception{
    public ServicoInexistente(String errorMessage) {
        super(errorMessage);
    }
}