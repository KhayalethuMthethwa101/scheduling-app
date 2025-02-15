package com.events_manager.controller;
import com.events_manager.util.AuthenticationResponse;
import com.events_manager.util.JWTUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    private JWTUtility jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody String idToken) throws Exception {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        final String jwt = jwtTokenUtil.generateToken(uid);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

