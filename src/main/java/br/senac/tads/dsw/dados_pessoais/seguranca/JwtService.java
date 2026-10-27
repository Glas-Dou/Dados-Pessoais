
package br.senac.tads.dsw.dados_pessoais.seguranca;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiracao-segundos:3600}")
    private long expiracaoSegundos;

    /**
     * Gera um JWT a partir dos dados do usuário autenticado.
     * Monta as claims e delega a assinatura para jwtEncode().
     */
    public String gerarToken(UsuarioSistema usuario) {

        // Converte as authorities em uma lista de Strings.
        List<String> roles = new ArrayList<>();

        for (GrantedAuthority authority : usuario.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        Instant agora = Instant.now();
        Instant expiracao = agora.plusSeconds(expiracaoSegundos);

        // Define o payload do token (claims).
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(usuario.getUsername())
                .issuer("dados-pessoais-api")
                .issueTime(Date.from(agora))
                .expirationTime(Date.from(expiracao))
                .claim("roles", roles)
                .build();

        return jwtEncode(claims);
    }

    private String jwtEncode(JWTClaimsSet claims) {
        try {
            // Deriva uma chave de 256 bits usando SHA-256.
            byte[] keyBytes = MessageDigest.getInstance("SHA-256")
                    .digest(jwtSecret.getBytes(StandardCharsets.UTF_8));

            MACSigner signer = new MACSigner(keyBytes);

            // Cria o JWT com o algoritmo HS256 e as claims.
            SignedJWT jwt = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claims
            );

            // Assina o token.
            jwt.sign(signer);

            // Retorna o token serializado.
            return jwt.serialize();

        } catch (JOSEException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao assinar token JWT", e);
        }
    }
}
