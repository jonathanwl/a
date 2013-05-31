package com.wasoft.jetty;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;


public class DynaHandler extends AbstractHandler
{
    public void handle(String target, 
    					HttpServletRequest request, 
    					HttpServletResponse response, 
    					int dispatch) throws IOException, ServletException
    {
        Request base_request = (request instanceof Request) ? 
        		(Request)request : HttpConnection.getCurrentConnection().getRequest();
        
        base_request.setHandled(true);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        //<script language=\"JavaScript\" src=\"./scripts/test.js\"></script><script>pt(\"lll\");</script>
        StringBuffer sb = new StringBuffer("<html><head></head><body>");
        sb.append("<h1>Hello DynaHandler</h1>");
        sb.append("</body></html>");
        response.getWriter().println(sb.toString());
    }
}