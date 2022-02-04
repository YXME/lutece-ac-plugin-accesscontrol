package fr.paris.lutece.plugins.accesscontrol.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mock.web.MockHttpServletRequest;

import fr.paris.lutece.plugins.accesscontrol.business.AccessController;
import fr.paris.lutece.plugins.accesscontrol.business.AccessControllerHome;
import fr.paris.lutece.plugins.accesscontrol.business.UserCodeControllerData;
import fr.paris.lutece.plugins.accesscontrol.business.UserCodeControllerDataHome;
import fr.paris.lutece.plugins.accesscontrol.business.config.IAccessControllerConfigDAO;
import fr.paris.lutece.plugins.accesscontrol.business.config.UserCodeAccessControllerConfig;
import fr.paris.lutece.plugins.accesscontrol.business.config.UserCodeAccessControllerConfigDAO;
import fr.paris.lutece.portal.service.security.LoginRedirectException;
import fr.paris.lutece.portal.service.security.LuteceAuthentication;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.test.LuteceTestCase;

public class LuteceUserCodeAccessControllerTypeTest extends LuteceTestCase
{
    private static final int ID_ACCESS_CONTROL = 1;
    private static final String USER = "user";
    private static final String CODE_OK = "codeOK";
    private static final String CODE_KO = "codeKO";
    private static final String ERROR_MSG = "Error Message";

    private IAccessControllerConfigDAO<UserCodeAccessControllerConfig> _dao = new UserCodeAccessControllerConfigDAO( );
    
    public void testValidate( ) throws UserNotSignedException
    {
        AccessController accessController = new AccessController( );
        accessController.setIdAccesscontrol( ID_ACCESS_CONTROL );
        AccessControllerHome.create( accessController );
        
        UserCodeAccessControllerConfig config = new UserCodeAccessControllerConfig( );
        config.setIdAccessController( accessController.getId( ) );
        config.setErrorMessage( ERROR_MSG );
        _dao.insert( config );
        
        UserCodeControllerData data = new UserCodeControllerData( );
        data.setIdAccessControl( ID_ACCESS_CONTROL );
        data.setUser( USER );
        data.setCode( CODE_OK );
        data.setValidityDate( Date.valueOf( LocalDate.now( ).plusDays( 1 ) ) );
        
        UserCodeControllerDataHome.create( data );
        
        LuteceUserCodeAccessControllerType controller = SpringContextService.getBean( LuteceUserCodeAccessControllerType.BEAN_NAME );
        

        LuteceUser user = getLuteceUser( );
        MockHttpServletRequest requestKO = new MockHttpServletRequest( );
        SecurityService.getInstance( ).registerUser( requestKO, user );
        requestKO.addParameter( "code", CODE_KO );
        
        MockHttpServletRequest requestOK = new MockHttpServletRequest( );
        SecurityService.getInstance( ).registerUser( requestOK, user );
        requestOK.addParameter( "code", CODE_OK );
        
        try {
        assertNull( controller.validate( requestOK, accessController ) );
        assertEquals( ERROR_MSG, controller.validate( requestKO, accessController ) );
        }
        catch (Exception e) {
            e.printStackTrace( );
        }
        MockHttpServletRequest requestNoUser = new MockHttpServletRequest( );
        requestOK.addParameter( "code", CODE_OK );
        
        try
        {
            controller.validate( requestNoUser, accessController );
            fail( );
        }
        catch (UserNotSignedException e)
        {
            // Expected behavior
        }
        
        controller.deleteConfig( accessController.getId( ) );
        UserCodeControllerDataHome.remove( USER, ID_ACCESS_CONTROL );
        AccessControllerHome.remove( accessController.getId( ) );
    }
    
    private LuteceUser getLuteceUser( )
    {
        return new LuteceUser( USER, new LuteceAuthentication( )
        {
            
            @Override
            public void updateDateLastLogin( LuteceUser user, HttpServletRequest request )
            {
                
            }
            
            @Override
            public void logout( LuteceUser user )
            {
                
            }
            
            @Override
            public LuteceUser login( String strUserName, String strUserPassword, HttpServletRequest request ) throws LoginException, LoginRedirectException
            {
                return null;
            }
            
            @Override
            public boolean isUsersListAvailable( )
            {
                return false;
            }
            
            @Override
            public boolean isUserInRole( LuteceUser user, HttpServletRequest request, String strRole )
            {
                return false;
            }
            
            @Override
            public boolean isMultiAuthenticationSupported( )
            {
                return false;
            }
            
            @Override
            public boolean isExternalAuthentication( )
            {
                return false;
            }
            
            @Override
            public boolean isDelegatedAuthentication( )
            {
                return false;
            }
            
            @Override
            public String getViewAccountPageUrl( )
            {
                return null;
            }
            
            @Override
            public Collection<LuteceUser> getUsers( )
            {
                return null;
            }
            
            @Override
            public LuteceUser getUser( String strUserLogin )
            {
                return null;
            }
            
            @Override
            public String [ ] getRolesByUser( LuteceUser user )
            {
                return null;
            }
            
            @Override
            public String getResetPasswordPageUrl( HttpServletRequest request )
            {
                return null;
            }
            
            @Override
            public String getPluginName( )
            {
                return null;
            }
            
            @Override
            public String getNewAccountPageUrl( )
            {
                return null;
            }
            
            @Override
            public String getName( )
            {
                return null;
            }
            
            @Override
            public String getLostPasswordPageUrl( )
            {
                return null;
            }
            
            @Override
            public String getLostLoginPageUrl( )
            {
                return null;
            }
            
            @Override
            public String getLoginPageUrl( )
            {
                return null;
            }
            
            @Override
            public String getIconUrl( )
            {
                return null;
            }
            
            @Override
            public LuteceUser getHttpAuthenticatedUser( HttpServletRequest request )
            {
                return null;
            }
            
            @Override
            public String getDoLogoutUrl( )
            {
                return null;
            }
            
            @Override
            public String getDoLoginUrl( )
            {
                return null;
            }
            
            @Override
            public String getAuthType( HttpServletRequest request )
            {
                return null;
            }
            
            @Override
            public String getAuthServiceName( )
            {
                return null;
            }
            
            @Override
            public LuteceUser getAnonymousUser( )
            {
                return null;
            }
            
            @Override
            public String getAccessDeniedTemplate( )
            {
                return null;
            }
            
            @Override
            public String getAccessControledTemplate( )
            {
                return null;
            }
            
            @Override
            public boolean findResetPassword( HttpServletRequest request, String strLogin )
            {
                return false;
            }
        } )
        {

            /**
             * 
             */
            private static final long serialVersionUID = 2282155535608886711L;
        };
    }
}
