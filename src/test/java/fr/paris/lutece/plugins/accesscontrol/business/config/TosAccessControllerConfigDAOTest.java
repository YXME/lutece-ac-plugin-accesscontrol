package fr.paris.lutece.plugins.accesscontrol.business.config;

import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.test.LuteceTestCase;

public class TosAccessControllerConfigDAOTest extends LuteceTestCase
{
    private static final int ID = 1;
    private static final String COMMENT_1 = "comment1";
    private static final String COMMENT_2 = "comment2";
    private static final String MSG_1 = "msg1";
    private static final String MSG_2 = "msg2";
    
    public void testDao( )
    {
        IAccessControllerConfigDAO<TosAccessControllerConfig> dao = SpringContextService.getBean( TosAccessControllerConfigDAO.BEAN_NAME );
        
        TosAccessControllerConfig config = new TosAccessControllerConfig( );
        config.setIdAccessController( ID );
        config.setComment( COMMENT_1 );
        config.setErrorMessage( MSG_1 );
        dao.insert( config );
        
        TosAccessControllerConfig loaded = dao.load( ID );
        assertEquals( COMMENT_1, loaded.getComment( ) );
        assertEquals( MSG_1, loaded.getErrorMessage( ));
        
        config.setComment( COMMENT_2 );
        config.setErrorMessage( MSG_2 );
        dao.store( config );
        loaded = dao.load( ID );
        assertEquals( COMMENT_2, loaded.getComment( ) );
        assertEquals( MSG_2, loaded.getErrorMessage( ));
        
        dao.delete( ID );
        loaded = dao.load( ID );
        assertNotNull( loaded );
    }
}