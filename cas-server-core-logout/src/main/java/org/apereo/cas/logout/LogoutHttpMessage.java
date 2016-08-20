package org.apereo.cas.logout;

import org.apereo.cas.util.http.HttpMessage;
import org.springframework.http.MediaType;

import java.net.URL;

/**
 * A logout http message that is accompanied by a special content type
 * and formatting.
 * @author Misagh Moayyed
 * @since 4.1.0
 */
public class LogoutHttpMessage extends HttpMessage {

    /** The parameter name that contains the logout request. */
    private final String logoutParameterName = "logoutRequest";
    
    private boolean prefixLogoutParameterName = true;
    
    /**
     * Constructs a logout message.
     *
     * @param url The url to send the message to
     * @param message Message to send to the url
     */
    public LogoutHttpMessage(final URL url, final String message, final boolean asynchronous) {
        super(url, message, asynchronous);
        setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    /**
     * {@inheritDoc}.
     * Prepends the string "{@code logoutRequest=}" to the message body.
     */
    @Override
    protected String formatOutputMessageInternal(final String message) {
        return (prefixLogoutParameterName ? logoutParameterName + '=' : "") 
                + super.formatOutputMessageInternal(message);
    }

    public void setPrefixLogoutParameterName(final boolean prefixLogoutParameterName) {
        this.prefixLogoutParameterName = prefixLogoutParameterName;
    }

    public String getLogoutParameterName() {
        return logoutParameterName;
    }
}
