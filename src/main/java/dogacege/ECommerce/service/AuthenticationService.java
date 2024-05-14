package dogacege.ECommerce.service;

import dogacege.ECommerce.config.JwtService;
import dogacege.ECommerce.dto.AuthenticationRequest;
import dogacege.ECommerce.dto.AuthenticationResponse;
import dogacege.ECommerce.dto.RegisterRequest;
import dogacege.ECommerce.entity.ShoppingCart;
import dogacege.ECommerce.entity.User;
import dogacege.ECommerce.enums.Role;
import dogacege.ECommerce.repository.ShoppingCartRepository;
import dogacege.ECommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ShoppingCartRepository shoppingCartRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        ShoppingCart shoppingCart = new ShoppingCart();

        var user = User
                .builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // save encoded password to the db
                .address(request.getAddress())
                .role(Role.USER)

                .build();

        repository.save(user); // User nesnesini veritabanına kaydet
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);


        Map<String, Object> claims = new HashMap<>();
        // Add user ID as a claim
        claims.put("id", (user).getUserId());

        //token olustur
        var jwtToken = jwtService.generateToken(claims,user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        var user = repository.findByEmail(request.getEmail()).orElseThrow();


        Map<String, Object> claims = new HashMap<>();
        // Add user ID as a claim
        claims.put("id", (user).getUserId());

        var jwtToken = jwtService.generateToken(claims,user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
