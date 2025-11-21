package com.gom.amigo_secreto.service;

import com.gom.amigo_secreto.model.User;
import com.gom.amigo_secreto.repository.UserRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public CustomOAuth2UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("id").toString();
        String email = oAuth2User.getAttribute("email");

        if ((email == null || email.isBlank() && "github".equals(provider))) {
            email = fetchGitHubPrimaryEmail(userRequest.getAccessToken().getTokenValue());
        }

        if (email == null || email.isBlank()) {
            email = providerId + "@" + provider + ".oauth2.local";
        }

        // GitHub: "login", Google: "name"
        String username = oAuth2User.getAttribute("login");
        if (username == null) username = oAuth2User.getAttribute("name");

        // GitHub: "avatar_url", Google: "picture"
        String avatarUrl = oAuth2User.getAttribute("avatar_url");
        if (avatarUrl == null) avatarUrl = oAuth2User.getAttribute("picture");

        String finalUsername = username;
        String finalAvatarUrl = avatarUrl;
        String finalEmail = email;

        User user = userRepository.findByProviderAndProviderId(provider, providerId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(finalEmail);
                    newUser.setProvider(provider);
                    newUser.setProviderId(providerId);
                    newUser.setUsername(finalUsername);
                    newUser.setAvatarUrl(finalAvatarUrl);
                    newUser.setRegisterDate(LocalDateTime.now());
                    return userRepository.save(newUser);
                });

        return oAuth2User;
    }

    private String fetchGitHubPrimaryEmail(String accessToken) {
        try {
            String url = "https://api.github.com/user/emails";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.set("Accept", "application/vnd.github.v3+json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<List<Map<String, Object>>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<Map<String, Object>>>() {}
                    );

            List<Map<String, Object>> emails = response.getBody();

            if (emails != null && !emails.isEmpty()) {
                for (Map<String, Object> emailData : emails) {
                    Boolean primary = (Boolean) emailData.get("primary");
                    Boolean verified = (Boolean)  emailData.get("verified");
                    if (Boolean.TRUE.equals(primary) && Boolean.TRUE.equals(verified)) {
                        return (String) emailData.get("email");
                    }
                }

                for (Map<String, Object> emailData : emails) {
                    Boolean verified = (Boolean) emailData.get("verified");
                    if (Boolean.TRUE.equals(verified)) {
                        return (String) emailData.get("email");
                    }
                }

                return (String) emails.get(0).get("email");
            }

        } catch (Exception e) {
            System.err.println("Error searching email in GitHub: " + e.getMessage());
        }

        return null;
    }
}
