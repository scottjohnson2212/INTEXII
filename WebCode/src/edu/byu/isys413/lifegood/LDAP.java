/**
 * 
 */
package edu.byu.isys413.lifegood;

import java.util.Hashtable;


import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


/**
 * @author lifeisgood21
 *
 */
public class LDAP {
	
	/**
     * login method of the program.
	 * @return 
	 * @return 
     */
    public static String loginLDAPByu() {
        // get the username
        String ryid = "lifegood";

        // get the password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        Object[] obj = { "Please enter the password:\n\n", passwordField };
        Object stringArray[] = {"OK","Cancel"};
        if (JOptionPane.showOptionDialog(null, obj, "Need password", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION);
        String password = new String(passwordField.getPassword());        
        
        // run the LDAP
        LDAP ldap = new LDAP();
        if (ldap.authenticate(ryid, password)) {
            System.out.println("Authentication worked!");
        }else{
            System.out.println("Authentication didn't work.");
            loginLDAPByu();
        }
        return ryid;

    }

    /**
     *  Authenticates a user.  If the "new InitialDirContext" doesn't throw
     *  an exception, the user and password validated with LDAP.  We could then
     *  use this DirContext to query the user's email and address information,
     *  but all we care about is authentication.
     */
    public boolean authenticate(String NetID, String Password) {
        try{
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://ldap.byu.edu/");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + NetID + ", ou=People, o=byu.edu");
            env.put(Context.SECURITY_CREDENTIALS, Password);
            DirContext ctx = new InitialDirContext(env);
            return true;
        }catch (NamingException e) {
            return false;
        }
    }
    
    
}
