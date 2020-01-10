package security;

import java.util.*;

public class SecurityConfig {

    public static final String roleAdmin = "admin";
    public static final String roleUser = "user";

    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    public static void init() {
        List<String> urlPatternsAdmin = new ArrayList<>();
        urlPatternsAdmin.add("/admin");

        List<String> urlPatternsUser = new ArrayList<>();
        urlPatternsUser.add("/info");

        mapConfig.put(roleAdmin, urlPatternsAdmin);
        mapConfig.put(roleUser, urlPatternsUser);
    }

    public static Set<String> getAllRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}