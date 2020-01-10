package security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String user;
    private String role = null;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, String role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (this.role == null) {
            return this.realRequest.isUserInRole(role);
        }
        return this.role.contains(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }

        return new Principal() {
            @Override
            public String getName() {
                return user;
            }
        };
    }
}