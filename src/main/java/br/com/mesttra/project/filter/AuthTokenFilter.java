package br.com.mesttra.project.filter;

import br.com.mesttra.project.data.UserRepository;
import br.com.mesttra.project.model.User;
import br.com.mesttra.project.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthTokenFilter (TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal (HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {

        String tokenWithoutBearer = tokenService.getTokenWithoutBearer(request);

        boolean isValid = tokenService.isTokenValid(tokenWithoutBearer);

        if (isValid) {
            doAuthUser(tokenWithoutBearer);
        }

        filterChain.doFilter(request, response);
    }

    private void doAuthUser (String tokenWithoutBearer) {
        Long userId = this.tokenService.getSub(tokenWithoutBearer);
        Optional<User> optionalUser = this.userRepository.findByIdWithJoinFetch(userId);
        User user = optionalUser.orElseThrow(RuntimeException::new);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
