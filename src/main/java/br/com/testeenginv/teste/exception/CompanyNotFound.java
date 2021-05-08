package br.com.testeenginv.teste.exception;

public class CompanyNotFound extends BussinesException {

    public CompanyNotFound() {
        super("Company não encontrada");
    }
}
