package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prinip {
    public static void main(String[] args) {
        System.out.print("inhai\n");
        DesafioMeuTimeApplication DMTP = new DesafioMeuTimeApplication();

        DMTP.incluirTime(2342L,"Barcelona", LocalDate.of(1923,5,23),
                "Azul","amarelo");
        DMTP.incluirTime(1342L,"Flarcelona", LocalDate.of(1923,5,23),
                "Azul","amarelo");
        DMTP.incluirJogador(423L,2342L,"Messi",LocalDate.of(1923,5,23)
        ,59, BigDecimal.valueOf(2340));
        DMTP.incluirJogador(433L,2342L,"Messi",LocalDate.of(1923,5,23)
                ,39, BigDecimal.valueOf(2340));
        //System.out.println("Maior id é: "+DMTP.buscarJogadorMaisVelho(2342L));
        //System.out.println("Maior id é: "+DMTP.buscarSalarioDoJogador(433L));
        //System.out.println("Maior id é: "+DMTP.buscarTimes());
        //DMTP.definirCapitao(433L);
        System.out.println("Maior id é: "+DMTP.buscarCapitaoDoTime(2342L));

    }
}
