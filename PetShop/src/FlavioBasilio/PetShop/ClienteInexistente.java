package PauloAugustoMartins.PetShop;

public class ClienteInexistente extends Exception{
    public ClienteInexistente(String errorMessage) {
        super(errorMessage);
    }
}
