package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.utils.Payload.JwtAuthenticationResponse;
import app.hesias.gabbler.utils.Payload.LoginRequest;
import app.hesias.gabbler.utils.Security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint() {
        return ResponseEntity.ok("Protected endpoint accessed");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
