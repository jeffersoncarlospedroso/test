package br.com.testeenginv.teste.exception;

public class CompanyNotUnique extends BussinesException {

    public CompanyNotUnique() {
        super("Company já cadastrada");
    }
}
