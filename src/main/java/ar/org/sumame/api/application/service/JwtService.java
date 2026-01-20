package ar.org.sumame.api.application.service;

import java.util.List;

public interface JwtService {

    boolean tokenValido(String token);

    String extraerUsername(String token);

    List<String> extraerRoles(String token);
}
